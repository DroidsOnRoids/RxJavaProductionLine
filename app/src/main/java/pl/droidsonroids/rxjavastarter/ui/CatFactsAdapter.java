package pl.droidsonroids.rxjavastarter.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import pl.droidsonroids.rxjavastarter.R;
import pl.droidsonroids.rxjavastarter.model.CatFactWithImage;

public class CatFactsAdapter extends RecyclerView.Adapter<CatFactsAdapter.ViewHolder> {

    private Context mContext;
    private List<CatFactWithImage> mCatFactWithImages;

    public CatFactsAdapter(final Context context) {
        this(context, new ArrayList<>());
    }

    public CatFactsAdapter(final Context context, final List<CatFactWithImage> catFactWithImages) {
        mContext = context;
        mCatFactWithImages = catFactWithImages;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cat_fact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CatFactWithImage catFactWithImage = mCatFactWithImages.get(position);
        holder.mTextCatFact.setText(catFactWithImage.getCatFact());
        Picasso.with(mContext)
                .load(catFactWithImage.getCatImageId())
                .centerCrop()
                .resizeDimen(R.dimen.cat_image_size, R.dimen.cat_image_size)
                .into(holder.mImageCat);
    }

    @Override
    public int getItemCount() {
        return mCatFactWithImages.size();
    }

    public void setCatFactWithImages(final List<CatFactWithImage> catFactWithImages) {
        mCatFactWithImages = catFactWithImages;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_cat) ImageView mImageCat;
        @Bind(R.id.text_cat_fact) TextView mTextCatFact;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
