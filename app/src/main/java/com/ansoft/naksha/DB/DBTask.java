package com.ansoft.naksha.DB;

import android.os.AsyncTask;

public class DBTask
  extends AsyncTask<Void, Void, Void>
{
  private IDBTaskListener mDownloadListener;
  
  public DBTask(IDBTaskListener paramIDBTaskListener)
  {
    this.mDownloadListener = paramIDBTaskListener;
  }
  
  @Override
protected Void doInBackground(Void... paramVarArgs)
  {
    if (this.mDownloadListener != null) {
      this.mDownloadListener.onDoInBackground();
    }
    return null;
  }
  
  @Override
protected void onPostExecute(Void paramVoid)
  {
    if (this.mDownloadListener != null) {
      this.mDownloadListener.onPostExcute();
    }
  }
  
  @Override
protected void onPreExecute()
  {
    if (this.mDownloadListener != null) {
      this.mDownloadListener.onPreExcute();
    }
  }
}

