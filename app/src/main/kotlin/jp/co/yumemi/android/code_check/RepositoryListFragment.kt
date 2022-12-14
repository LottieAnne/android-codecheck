/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryListBinding
import kotlinx.coroutines.flow.collect

class RepositoryListFragment: Fragment(R.layout.fragment_repository_list){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val binding= FragmentRepositoryListBinding.bind(view)

        val viewModel= RepositoryListViewModel(requireContext())

        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect {
                binding.searchInputText.setText(
                    it.repositoryList.toString(),
                    TextView.BufferType.EDITABLE
                )
            }
        }
        viewModel.searchResults( "android")
    }

    fun gotoRepositoryFragment(item: Item)
   {
//        val _action= OneFragmentDirections
//            .actionRepositoriesFragmentToRepositoryFragment(item= item)
//        findNavController().navigate(_action)
   }
}

val diff_util= object: DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean
    {
        return oldItem.name== newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean
    {
        return oldItem== newItem
    }

}

class CustomAdapter(
    private val itemClickListener: OnItemClickListener,
) : ListAdapter<Item, CustomAdapter.ViewHolder>(diff_util){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    interface OnItemClickListener{
    	fun itemClick(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
    	val _view= LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
    	return ViewHolder(_view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
    	val _item= getItem(position)
        (holder.itemView.findViewById<View>(R.id.repositoryNameView) as TextView).text=
            _item.name

    	holder.itemView.setOnClickListener{
     		itemClickListener.itemClick(_item)
    	}
    }
}
