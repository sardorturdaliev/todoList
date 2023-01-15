package uz.sardor.meningkundaligim.presenter.sreens.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import uz.sardor.meningkundaligim.R
import uz.sardor.meningkundaligim.databinding.FragmentMainBinding
import uz.sardor.meningkundaligim.presenter.sreens.editScreen.EditFragement
import uz.sardor.meningkundaligim.presenter.sreens.homescreen.HomeFragment
import uz.sardor.meningkundaligim.presenter.sreens.mainscreen.adapterViewpager.ViewPagerAdapter
import uz.sardor.meningkundaligim.presenter.sreens.screenProject.AllProjectFragment

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private lateinit var pagerAdapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list  = ArrayList<Fragment>()
        list.add(HomeFragment())
        list.add(AllProjectFragment())
        list.add(EditFragement())
        pagerAdapter = ViewPagerAdapter(this,list)

        binding.viewpager.adapter = pagerAdapter
        binding.viewpager.isUserInputEnabled = false

        binding.viewpager.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> binding.bottonNav.selectedItemId = R.id.menu_home
                    1 -> binding.bottonNav.selectedItemId = R.id.menu_document
                    else -> binding.bottonNav.selectedItemId = R.id.menu_add
                }
                super.onPageSelected(position)
            }
        })

        binding.bottonNav.setOnItemSelectedListener(object  : NavigationBarView.OnItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.menu_home -> binding.viewpager.setCurrentItem(0)
                    R.id.menu_document -> binding.viewpager.setCurrentItem(1)
                    R.id.menu_add -> binding.viewpager.setCurrentItem(2)
                }
                return true
            }
        })
        return binding.root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewpager.adapter = null
    }
}