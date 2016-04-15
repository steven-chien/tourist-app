package hk.edu.polyu.comp.hellohongkong.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class NearFieldCommunicationActivity extends AppCompatActivity {

    public static final String MIME_TEXT_PLAIN = "text/plain";
    public static final String ACTIVITY_TAG = "NFCActivity";

    private Resources mvResources;

    private TextView mNfcIdTextView;
    private NfcAdapter mNfcAdapter;

    private Toolbar mvToolbar;


    /*
     |
     |  Android activity lifecycle events
     |
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_field_communication);
        mvResources = getResources();

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

        mNfcIdTextView = (TextView) findViewById(R.id.nfcIdTextView);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // Make sure the device supports NFC, otherwise, say goodbye ;)
        if (mNfcAdapter == null) {
            Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Make sure the NFC function is switched on
        if (!mNfcAdapter.isEnabled()) {
            mNfcIdTextView.setText("NFC is disabled. Please switch it on and enter this application again.");
        } else {
            mNfcIdTextView.setText("NFC is ready. Place the NFC tag towards your device.");
        }

        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        stopForegroundDispatch(this, mNfcAdapter);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }


    /*
     |
     |  Handing any intent received particularly NFC intent)
     |
     */
    private void handleIntent(Intent intent) {
        String action = intent.getAction();

        if (action != null && action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            String type = intent.getType();

            if (type.equals(MIME_TEXT_PLAIN)) {
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                new NdefReaderTask().execute(tag);
            } else {
                Log.d(ACTIVITY_TAG, "Wrong mime type: " + type);
            }
        }
    }


    /*
     |
     |  NFC detection dispatch enable and disable methods
     |
     */
    public static void setupForegroundDispatch(Activity activity, NfcAdapter adapter) {
        Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("Check your mime type.");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    public static void stopForegroundDispatch(Activity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }


    /*
     |
     |  Private inner class
     |
     */
    private class NdefReaderTask extends AsyncTask<Tag, Void, String> {

        @Override
        protected String doInBackground(Tag... params) {
            Tag tag = params[0];

            Ndef ndef = Ndef.get(tag);
            if (ndef == null) {
                return null;  // NDEF is not supported by this Tag
            }

            NdefMessage ndefMessage = ndef.getCachedNdefMessage();

            NdefRecord[] records = ndefMessage.getRecords();
            for (NdefRecord ndefRecord : records) {
                if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                    try {
                        return readText(ndefRecord);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(ACTIVITY_TAG, "Unsupported Encoding", e);
                    }
                }
            }

            return null;
        }


        /*
         |
         |  See NFC forum specification for "Text Record Type Definition" at 3.2.1
         |
         |  http://www.nfc-forum.org/specs/
         |
         |  bit_7 defines encoding
         |  bit_6 reserved for future use, must be 0
         |  bit_5..0 length of IANA language code
         |
         */
        private String readText(NdefRecord record) throws UnsupportedEncodingException {
            byte[] payload = record.getPayload();

            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";  // Get the Text Encoding
            int languageCodeLength = payload[0] & 0063;  // Get the Language Code

            return new String(
                    payload,
                    languageCodeLength + 1,
                    payload.length - languageCodeLength - 1,
                    textEncoding);  // Get the Text
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Intent intent = new Intent(NearFieldCommunicationActivity.this, TourSpotDetailActivity.class);
                intent.putExtra("id", result);
                startActivity(intent);
                NearFieldCommunicationActivity.this.finish();
            }
        }
    }

}  // End NearFieldCommunicationActivity class
