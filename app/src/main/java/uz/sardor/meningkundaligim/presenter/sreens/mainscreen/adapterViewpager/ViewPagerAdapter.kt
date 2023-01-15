package uz.sardor.meningkundaligim.presenter.sreens.mainscreen.adapterViewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.sardor.meningkundaligim.presenter.sreens.mainscreen.MainFragment

class ViewPagerAdapter(fragmentActivity: MainFragment, var list: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return  list.get(position)
    }


}