package ro.baratheon.doctorhouse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ro.baratheon.doctorhouse.entity.Product;

public class CustomAdapter extends ArrayAdapter<Product> {

    private ArrayList<Product> dataSet;
    public ArrayList<Product> selectedProducts = new ArrayList<Product>();
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        CheckBox checkbox;
        TextView checkBoxLabel;
    }

    public CustomAdapter(ArrayList<Product> data, Context context) {
        super(context, R.layout.row, data);
        this.dataSet = data;
        this.mContext = context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row, parent, false);
        viewHolder.name = (TextView) convertView.findViewById(R.id.productName);
        viewHolder.price = (TextView) convertView.findViewById(R.id.productPrice);
        viewHolder.imageView = (ImageView) convertView.findViewById(R.id.productImage);
        viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.checkBox);
        viewHolder.checkBoxLabel = (TextView) convertView.findViewById(R.id.checkBoxLabel);

        result = convertView;
        if (!dataModel.getArCompatible()) {
            viewHolder.checkbox.setVisibility(View.INVISIBLE);
            viewHolder.checkBoxLabel.setVisibility(View.INVISIBLE);
        }
        convertView.setTag(viewHolder);

        convertView.setId(dataModel.getId());
        if (selectedProducts.indexOf(dataModel) != -1) {
            viewHolder.checkbox.setChecked(true);
        }
        viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                Product product = dataSet.get(getPosition);
                if (isChecked) {
                    if (selectedProducts.indexOf(product) == -1) {
                        selectedProducts.add(product);
                    }
                } else {
                    if (selectedProducts.indexOf(product) != -1) {
                        selectedProducts.remove(product);
                    }
                }
                product.setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
            }
        });
        lastPosition = position;
        viewHolder.checkbox.setTag(position);
        viewHolder.name.setText(dataModel.getName());
        viewHolder.price.setText(String.valueOf(dataModel.getPrice()));
        viewHolder.imageView.setImageBitmap(this.getBitmapFromURL(dataModel.getImage()));
        // Return the completed view to render on screen
        return result;
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.ic_launcher);
        }
    }
}
