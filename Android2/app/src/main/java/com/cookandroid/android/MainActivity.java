package com.cookandroid.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;

    Fragment frag_info, frag_toggle,
            frag_sys, frag_ctrl,
            frag3, frag_connect;
    SystemFragment fragment_sys;
    private BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> mPairedDevices;
    List<String> mListPairedDevices;

    Handler mBluetoothHandler;
    public static ConnectedBluetoothThread mThreadConnectedBluetooth;
    BluetoothDevice mBluetoothDevice;
    BluetoothSocket mBluetoothSocket;

    final static int BT_REQUEST_ENABLE = 1;
    final static int BT_MESSAGE_READ = 2;
    final static int BT_CONNECTING_STATUS = 3;
    final static UUID BT_UUID = UUID.fromString("00000003-0000-1000-8000-00805F9B34FB");

    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING=2;
    static final int STATE_CONNECTED=3;
    static final int STATE_CONNECTION_FAILED=4;
    static final int STATE_MESSAGE_RECEIVED=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        manager = getSupportFragmentManager();
        fragment_sys = (SystemFragment) manager.findFragmentById(R.id.frag_sys);

        frag_info = new LettuceFragment();
        frag_toggle = new ToggleFragment();
        frag_sys = new SystemFragment();

        frag_ctrl = new ControlFragment();
        frag_connect = new ConnectionFragment();
        frag3 = new CalenderFragment();
        frag_connect = new ConnectionFragment();
        manager.beginTransaction().replace(R.id.container, frag_info)
                .replace(R.id.container_main, frag_toggle).commit();

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("식물 선택"));
        tabs.addTab(tabs.newTab().setText("시스템 관리"));
        tabs.addTab(tabs.newTab().setText("연결 관리"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment frselected = null;
                Fragment frselected2 = null;
                if (position == 0) {
                    frselected = frag_info;
                    frselected2 = frag_toggle;
                } else if (position == 1) {
                    frselected = frag_sys;
                    frselected2 = frag_ctrl;
                } else if (position == 2) {
                    frselected = frag3;
                    frselected2 = frag_connect;
                }

                manager.beginTransaction().replace(R.id.container, frselected)
                        .replace(R.id.container_main, frselected2).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab){
            }
        });

    // BLUETOOTH
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void toggle_info(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void sendData(String str) {
        if(mThreadConnectedBluetooth != null) {
            mThreadConnectedBluetooth.write(str);
        }
    }

    public void bluetoothON() {
        if(bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "블루투스에서 지원하지않는 기기입니다.", Toast.LENGTH_LONG).show();
        }
        else {
            if (bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "블루투스가 이미 활성화 되어 있습니다.",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "블루투스가 활성화 되어 있지 않습니다.",
                        Toast.LENGTH_LONG).show();
                Intent intentBluetoothEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intentBluetoothEnable, BT_REQUEST_ENABLE);
            }
        }
    }

    public void bluetoothOFF() {
        if(bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되었습니다.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 이미 비활성화되어 있습니다.",Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case BT_REQUEST_ENABLE:
                if(resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(),"블루투스 활성화", Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void listPairedDevices() {
        if (bluetoothAdapter.isEnabled()) {
            mPairedDevices = bluetoothAdapter.getBondedDevices();

            if (mPairedDevices.size() > 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("장치 선택");

                mListPairedDevices = new ArrayList<String>();
                for (BluetoothDevice device : mPairedDevices) {
                    mListPairedDevices.add(device.getName());
                }
                final CharSequence[] items = mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);
                mListPairedDevices.toArray(new CharSequence[mListPairedDevices.size()]);

                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        connectSelectedDevice(items[item].toString());
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "블루투스가 비활성화 되어 있습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what)
            {
                case STATE_LISTENING:
                    //status.setText("Listening");
                    break;
                case STATE_CONNECTING:
                    //status.setText("Connecting");
                    break;
                case STATE_CONNECTED:
                    fragment_sys.setConnectState(1);
                    break;
                case STATE_CONNECTION_FAILED:
                    fragment_sys.setConnectState(0);
                    //status.setText("Connection Failed");
                    break;
                case STATE_MESSAGE_RECEIVED:
                    byte[] readBuff= (byte[]) msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    if(tempMsg.contains("v"))
                        fragment_sys.setData(tempMsg.substring(1));
                    else if(tempMsg.contains("l"))
                        fragment_sys.setLevelState(tempMsg.substring(1));
                    break;
            }
            return true;
        }
    });

    void connectSelectedDevice(String selectedDeviceName) {
        for(BluetoothDevice tempDevice : mPairedDevices) {
            if (selectedDeviceName.equals(tempDevice.getName())) {
                mBluetoothDevice = tempDevice;
                break;
            }
        }
        try {
            mBluetoothSocket = createBluetoothSocket(mBluetoothDevice);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "블루투스 연결 중 소켓 생성에 실패했습니다.", Toast.LENGTH_SHORT).show();
        }

        try {
            mBluetoothSocket.connect();
        } catch (IOException e) {
            try {
                mBluetoothSocket.close();
                Toast.makeText(getApplicationContext(), "블루투스 연결 중 소켓이 종료되었습니다.", Toast.LENGTH_SHORT).show();
            } catch(IOException e2) {
                Toast.makeText(getApplicationContext(), "블루투스 연결 중 소켓이 종료가 실패되었습니다.", Toast.LENGTH_LONG).show();
            }
        }

        mThreadConnectedBluetooth = new ConnectedBluetoothThread(mBluetoothSocket);
        mThreadConnectedBluetooth.start();
    }

    private class ConnectedBluetoothThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedBluetoothThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        @Override
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.available();
                    if (bytes != 0) {
                        buffer = new byte[1024];
                        SystemClock.sleep(100); //pause and wait for rest of data. Adjust this depending on your sending speed.
                        bytes = mmInStream.available(); // how many bytes are ready to be read?
                        bytes = mmInStream.read(buffer, 0, bytes); // record how many bytes we actually read
                        handler.obtainMessage(STATE_MESSAGE_RECEIVED,bytes,-1,buffer).sendToTarget();
                        Message message=Message.obtain();
                        message.what=STATE_CONNECTED;
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message=Message.obtain();
                    message.what=STATE_CONNECTION_FAILED;
                    handler.sendMessage(message);

                    break;
                }
            }
        }

        public void write(String str) {
            byte[] bytes = str.getBytes();
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "소켓 해제 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
            return (BluetoothSocket) m.invoke(device, 1);

        } catch (Exception e) {
            //Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_UUID);
    }
}

//https://coding-factory.tistory.com/126

