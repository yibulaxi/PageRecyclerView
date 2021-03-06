package com.ckr.pagesnaphelper.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ckr.pagesnaphelper.R;
import com.ckr.pagesnaphelper.model.Item;
import com.ckr.pageview.adapter.BasePageAdapter;


/**
 * Created by PC大佬 on 2018/1/14.
 */

public class MainAdapter extends BasePageAdapter<Item, MainAdapter.MainHolder> {
	private static final String TAG = "MainAdapter";
	public static final int MAX_VALUE = 28800;
	private boolean isShowDeleteIcon;
	private int mLayoutId;

	public MainAdapter(Context context, @LayoutRes int itemLayoutId) {
		super(context);
		mLayoutId = itemLayoutId;
	}

	@Override
	public int getItemCount() {
		if (mIsLooping) {
			return super.getItemCount() == 0 ? 0 : MAX_VALUE;
		}
		return super.getItemCount();
	}

	@Override
	protected int getLayoutId(int viewType) {
		return mLayoutId;
	}

	@Override
	protected MainHolder getViewHolder(View itemView, int viewType) {
		return new MainHolder(itemView);
	}

	@Override
	protected void convert(MainHolder holder, final int position, Item originItem, final int adjustedPosition, Item item) {
		if (mLayoutFlag == GRID) {
			int page = adjustedPosition % (mRow * mColumn * 6);
			if (page < mRow * mColumn) {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color1));
			} else if (page < mRow * mColumn * 2) {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color2));
			} else if (page < mRow * mColumn * 3) {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color3));
			} else if (page < mRow * mColumn * 4) {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color4));
			} else if (page < mRow * mColumn * 5) {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color5));
			} else {
				holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.color6));
			}
		} else {
			int page = adjustedPosition % 6;
			if (page < 1) {
				holder.imageView.setImageResource(R.drawable.bg_color1);
			} else if (page < 2) {
				holder.imageView.setImageResource(R.drawable.bg_color2);
			} else if (page < 3) {
				holder.imageView.setImageResource(R.drawable.bg_color3);
			}else if (page < 4) {
				holder.imageView.setImageResource(R.drawable.bg_color4);
			}else if (page < 5) {
				holder.imageView.setImageResource(R.drawable.bg_color5);
			}else if (page < 6) {
				holder.imageView.setImageResource(R.drawable.bg_color6);
			}
		}
		if (item == null) {
			holder.relativeLayout.setVisibility(View.INVISIBLE);
			holder.itemView.setOnLongClickListener(null);
			holder.imageButton.setOnClickListener(null);
		} else {
			holder.relativeLayout.setVisibility(View.VISIBLE);
			holder.textView.setText(item.getName());
			holder.itemView.setOnLongClickListener(new OnItemLongClickListener(adjustedPosition));
			holder.imageButton.setOnClickListener(new OnItemClickListener(adjustedPosition));
			holder.imageButton.setVisibility(isShowDeleteIcon ? View.VISIBLE : View.GONE);
		}
	}

	class MainHolder extends RecyclerView.ViewHolder {
		private TextView textView;
		private RelativeLayout relativeLayout;
		private ImageButton imageButton;
		private ImageView imageView;

		public MainHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView.findViewById(R.id.textView);
			relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
			imageButton = (ImageButton) itemView.findViewById(R.id.imageButton);
			imageView = (ImageView) itemView.findViewById(R.id.imageView);
		}
	}

	class OnItemLongClickListener implements View.OnLongClickListener {
		private int mPosition;

		public OnItemLongClickListener(int position) {
			mPosition = position;
		}

		@Override
		public boolean onLongClick(View v) {
			isShowDeleteIcon = !isShowDeleteIcon;
			notifyDataSetChanged();
			return true;
		}
	}

	class OnItemClickListener implements View.OnClickListener {
		private int mTargetPos;

		public OnItemClickListener(int adjustedPos) {
			mTargetPos = adjustedPos;
		}

		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == R.id.imageButton) {
				removeItem(mTargetPos);
			}
		}
	}

}
