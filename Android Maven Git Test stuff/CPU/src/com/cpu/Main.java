package com.cpu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.util.Log;

public class Main extends Activity {
	
    public Process process;
	private String mCpuCurrentFreq;
	public Preference mCurrentFreqButton;
	
		private String mSamplingRateMax;
		private String mSamplingRateMin;
		private String mScalingGovernor;


    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loadCurrentCPUFreq();
    }
    
    
    
    public void loadCurrentCPUFreq() {
    	
    	mSamplingRateMin = execCommand("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq");
        mSamplingRateMax = execCommand("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq");
        mScalingGovernor = execCommand("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor");

    	
        // Find current cpu frequency (requires root privs)
        mCpuCurrentFreq = execCommand("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
        mCurrentFreqButton.setSummary(mCpuCurrentFreq.substring(0, (mCpuCurrentFreq.length() -3)) + " MHz (" + mSamplingRateMin.substring(0, (mSamplingRateMin.length() -3)) + "-" + mSamplingRateMax.substring(0, (mSamplingRateMax.length() -3)) + ")");
        
        Log.v("mCpuCurrentFreq", ""+mCpuCurrentFreq);
        Log.v("mCurrentFregBut", ""+mCurrentFreqButton.getSummary());
    }

    
    public String execCommand(String command) {
        String outPut = "";
        DataInputStream osRes;
        DataOutputStream os;
        try {
            Runtime rt = Runtime.getRuntime();
            process = rt.exec("su");
            os = new DataOutputStream(process.getOutputStream());
            osRes = new DataInputStream(process.getInputStream());
            os.writeBytes(command + "\n");
            os.flush();
            outPut = osRes.readLine();
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (IOException e) {
            // Ignore
        } catch (InterruptedException e) {
            // Ignore
        }
        return outPut;
    }

}