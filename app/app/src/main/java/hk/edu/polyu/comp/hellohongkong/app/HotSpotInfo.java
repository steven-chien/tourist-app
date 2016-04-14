package hk.edu.polyu.comp.hellohongkong.app;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhoumoyuan on 4/12/16.
 */
public class HotSpotInfo extends AppCompatActivity {
    private TourSpotManager svTourSpotManager;
    private Resources mvResources;
    private Toolbar mvToolbar;

    private GridView mvGridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hot_spot);
        mvResources = getResources();
        svTourSpotManager = TourSpotManager.getInstance();

        mvToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(mvToolbar);
        ActionBar lvActionBar = getSupportActionBar();
        lvActionBar.setDisplayHomeAsUpEnabled(true);
        if (mvToolbar != null) {
            mvToolbar.setTitle(mvResources.getString(R.string.app_name));
        }

        mvGridView = (GridView) findViewById(R.id.gridView);
        setupUI("All");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tour_hot_spot_map, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        switch (pItem.getItemId()) {
            case R.id.action_bar_hongkong:
                setupUI("Hong Kong");
                return true;
            case R.id.action_bar_kowloon:
                setupUI("Kowloon");
                return true;
            case R.id.action_bar_newterritories:
                setupUI("New Territories");
                return true;
            case R.id.action_bar_all:
                setupUI("All");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(pItem);

        }
    }

    public void setupUI(String pRegion) {
        ArrayList<TourSpot> lvTourSpotList = svTourSpotManager.getTourSpotList();
        ArrayList<HashMap<String, Object>> lvImageList = new ArrayList<HashMap<String, Object>>();

        try {
            for (TourSpot lvTourSpot : lvTourSpotList) {
                if (pRegion.equals("All") || pRegion.equals(lvTourSpot.getHKRegion())) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    Bitmap bm = new ImageUtil().execute(lvTourSpot.getImageURL()).get();
                    map.put("image", scaleCenterCrop(bm, 400, 500));
                    map.put("text", lvTourSpot.getName());
                    map.put("id", lvTourSpot.getID());
                    lvImageList.add(map);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ExtendedSimpleAdapter simpleAdapter = new ExtendedSimpleAdapter(getApplicationContext(), lvImageList, R.layout.items, new String[] { "image", "text", "id" }, new int[] {R.id.image, R.id.title });
        if (mvGridView != null) {
            mvGridView.setAdapter(simpleAdapter);
            mvGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    TextView t = (TextView) view.findViewById(R.id.title);
                    Intent i = new Intent(getApplicationContext(), TourSpotDetailActivity.class);
                    i.putExtra("id", view.getTag().toString());
                    i.putExtra("name", t.getText().toString());
                    startActivity(i);
                }
            });
        }
    }

    public Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }
}
