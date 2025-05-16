package com.trioscg.androidapp5.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.trioscg.androidapp5.R
import com.trioscg.androidapp5.utils.PlaylistManager

class SelectPlaylistActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var createButton: Button
    private lateinit var newNameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_playlist)

        // Bind UI elements
        listView = findViewById(R.id.playlistListView)
        createButton = findViewById(R.id.createButton)
        newNameInput = findViewById(R.id.newPlaylistName)

        // Retrieve all existing playlists
        val playlistNames = PlaylistManager.getAllPlaylistNames().toList()

        // Set adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, playlistNames)
        listView.adapter = adapter

        // Playlist selection
        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = playlistNames[position]
            setResult(RESULT_OK, intent.putExtra("playlist_name", selected))
            finish()
        }

        // Create new playlist
        createButton.setOnClickListener {
            val newName = newNameInput.text.toString().trim()
            if (newName.isNotEmpty()) {
                setResult(RESULT_OK, intent.putExtra("playlist_name", newName))
                finish()
            } else {
                Toast.makeText(this, "Enter a playlist name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
