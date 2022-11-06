package com.miso.vyns.data

import com.miso.vyns.model.VynsAlbum

class DataSource {

    fun loadAlbums(): List<VynsAlbum> {
        return listOf<VynsAlbum>(
            VynsAlbum(1, "Titulo1", "Imagen1", "Hoy1", "Desc1", "Gen1", "RC1"),
            VynsAlbum(2, "Titulo2", "Imagen2", "Hoy2", "Desc2", "Gen2", "RC2"),
            VynsAlbum(3, "Titulo3", "Imagen3", "Hoy3", "Desc3", "Gen3", "RC3")
        )
    }
}