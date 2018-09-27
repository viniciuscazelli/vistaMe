package bt.unicamp.ft.m183711_v188110.vista_me.interfaces;

import android.support.v4.app.Fragment;

public interface FragmentManagerActivity {
    public void OpenFragment(Fragment f, String tag,boolean addToBackStack);
    public void RemoveFragment(Fragment f);
}
