package com.amaker.mp.fileupload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amaker.mp.R;

public class FileExplorerActivity extends ListActivity {

	private List<String> items = null;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.directory_list);
		fill(new File("/").listFiles());
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int selectionRowID = (int) position;
		if (selectionRowID == 0) {
			fillWithRoot();
		} else {
			File file = new File(items.get(selectionRowID));
			if (file.isDirectory()) {
				fill(file.listFiles());
			} else {
				String path = file.getAbsolutePath();
				Intent intent = this.getIntent();
				intent.putExtra("filePath", path);
				FileExplorerActivity.this.setResult(0, intent);
				FileExplorerActivity.this.finish();
			}
		}
	}

	private void fillWithRoot() {
		fill(new File("/").listFiles());
	}

	private void fill(File[] files) {
		items = new ArrayList<String>();
		items.add(getString(R.string.to_top));
		for (File file : files)
			items.add(file.getPath());
		ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
				R.layout.file_row, items);
		setListAdapter(fileList);
	}
}