package jp.co.yumemi.android.code_check.repositoryDetail

import android.os.Parcel
import android.os.Parcelable
import jp.co.yumemi.android.code_check.domain.RepositoryInfo
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import kotlinx.serialization.json.Json

@Parcelize
@TypeParceler<RepositoryInfo, RepositoryInfoParceler>
data class RepositoryDetailArg (
    val repositoryInfo : RepositoryInfo,
) : Parcelable

internal object RepositoryInfoParceler : Parceler<RepositoryInfo> {
    override fun create(parcel: Parcel): RepositoryInfo {
        val string = parcel.readString()!!
        return Json.decodeFromString(RepositoryInfo.serializer(), string)
    }

    override fun RepositoryInfo.write(parcel: Parcel, flags: Int) {
        val json = Json.encodeToString(RepositoryInfo.serializer(), this)
        parcel.writeString(json)
    }
}