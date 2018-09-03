package com.ansoft.naksha.DB;

public abstract interface IDBTaskListener
{
  public abstract void onDoInBackground();
  
  public abstract void onPostExcute();
  
  public abstract void onPreExcute();
}
