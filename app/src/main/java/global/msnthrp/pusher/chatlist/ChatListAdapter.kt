package global.msnthrp.pusher.chatlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import global.msnthrp.pusher.databinding.ItemChatListBinding
import global.msnthrp.pusher.domain.entity.User

class ChatListAdapter : RecyclerView.Adapter<ChatListAdapter.ChatListItemViewHolder>() {

    private val items = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChatListBinding.inflate(inflater, parent, false)
        return ChatListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatListItemViewHolder, position: Int) {
        items.getOrNull(position)?.also(holder::bind)
    }

    override fun getItemCount(): Int = items.size

    fun update(items: List<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ChatListItemViewHolder(
        private val binding: ItemChatListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            binding.tvChatTitle.text = item.name
        }
    }
}