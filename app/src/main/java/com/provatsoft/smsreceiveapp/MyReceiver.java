package com.provatsoft.smsreceiveapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		 Toast.makeText(context,intent.getAction(), Toast.LENGTH_SHORT).show();
	       if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
	       {
	    	   
	    	   
	    	   Bundle bundle=intent.getExtras();
	    	   
	           SmsMessage[] smsmessage=null;
	           String number="";
	           String message="";
	           
	           if(bundle!=null)
	           {
	                 Object[] pdus=(Object[])bundle.get("pdus");

	                 smsmessage=new SmsMessage[pdus.length];
	                 for(int i=0;i<pdus.length;i++)
	                 {
	                         smsmessage[i]= SmsMessage.createFromPdu((byte[])pdus[i]);
	                         number=smsmessage[i].getOriginatingAddress();
	                         message+=smsmessage[i].getMessageBody();
	                 }
	            }
	           
	    		Toast.makeText(context,number+" : "+message, Toast.LENGTH_LONG).show();
	    	  
    		   startActivity(context, number, message);
    		   
    		   
	       }
	}

	private void startActivity(Context context, String number, String message) {
		 
 	   Intent activityIntent=new Intent(context,MainActivity.class);

 	   activityIntent.putExtra("message", message);
 	   activityIntent.putExtra("number", number);
 	   
		   activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   context.startActivity(activityIntent);
		
	}

}
