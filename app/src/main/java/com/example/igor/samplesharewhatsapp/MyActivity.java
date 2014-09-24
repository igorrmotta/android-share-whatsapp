package com.example.igor.samplesharewhatsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyActivity extends Activity
{
	EditText m_EditText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		m_EditText = (EditText) findViewById(R.id.editText);
		Button btnShare = (Button) findViewById(R.id.btnShare);
		btnShare.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ShareWhatsApp();
			}
		});
	}

	private void ShareWhatsApp()
	{
		PackageManager pm = getPackageManager();
		try
		{

			Intent waIntent = new Intent(Intent.ACTION_SEND);
			waIntent.setType("text/plain");
			String text = m_EditText.getText().toString();

			PackageInfo info = pm.getPackageInfo("com.whatsapp",
					PackageManager.GET_META_DATA);
			//Check if package exists or not. If not then code
			//in catch block will be called
			waIntent.setPackage("com.whatsapp");

			waIntent.putExtra(Intent.EXTRA_TEXT, text);
			startActivity(Intent.createChooser(waIntent, "Share with"));

		}
		catch (PackageManager.NameNotFoundException e)
		{
			Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
