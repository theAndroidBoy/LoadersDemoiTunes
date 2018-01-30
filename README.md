# LoadersDemoiTunes
simplest demo app for loaders
 
## Steps for implementing Loaders in Android

#### 1- Make a Loader ,this is done by creating a class that extends AsyncTaskLoader<D> and implement its methods onStartLoading() and loadInBackground()
 
    public class SimpleLoader extends AsyncTaskLoader<String> {
    private static final String TAG = "flow";
    public SimpleLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.i(TAG, "onStartLoading: without ForceLoad it won't go to loadinBackground");
        Log.i(TAG, "onStartLoading: this will be called second "+ Thread.currentThread().getId());
    }

    @Override
    public String loadInBackground() {
        Log.i(TAG, "loadInBackground: this will be called third "+ Thread.currentThread().getId());        
    }
     } //class closing bracket
  
#### 2- implement  LoaderManager.LoadCallBacks interface by giving body to its methods.
    
    public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<D> 

#### 3- inside onCreate() of Activity or anywhere else initiate a loader.
     getSupportLoaderManager().initLoader(0, null, this);

### What will happen when we initiate Loader using Loader Manager
    -once we intiate loader with unique id 0 using getSupportLoaderManager().initLoader(0, null, this); 
     the system will check if loader with id 0 exist ? if no than go to call back method onCreateLoader() of LoaderManager 
     if yes than go to call back method onLoadFinished() of LoaderManager.
 
### Whats inside onCreateLoader()
    -onCreateLoader() has return type <D>
    -inside this method we create the Loader.
    new SimpleLoader(this);
### What next
    -once Loader is created inside the onCreateLoader() ,the onStartLoading method of Loader is called .
    -but after onStartLoading() method loadinBackground() is called only if we have put code forceLoad inside the onStartLoading method.
    -loadinBackground() works on background thread.
    -loadinBackground() has returns type <D> so it returns that value.
    -this returned value goes directly to onCreateLoader() callback.
    -at which point the Loader is Finished loading and onLoadFinished() callback is called.
    -inside onLoadFinished() we can update the UI.
