package uz.sardor.meningkundaligim.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_extradata")
data class ExtraDataEnity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "col_extradata")
    val extradata: String,
    val key: Int,

    @ColumnInfo(defaultValue = "false")
    var isCheked: Boolean


)