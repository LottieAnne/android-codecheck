package jp.co.yumemi.android.code_check.repositoryDetail

import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import jp.co.yumemi.android.code_check.domain.RepositoryInfo
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass

@Parcelize
@TypeParceler<RepositoryInfo, RepositoryInfoParceler>
@Serializable
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

private val RepositoryDetailArg_NavTypeGenerated = object : NavType<RepositoryDetailArg>(isNullableAllowed = true) {
    override val name: String get() = "RepositoryDetailArg"

    override fun put(bundle: Bundle, key: String, value: RepositoryDetailArg) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): RepositoryDetailArg? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): RepositoryDetailArg {
        return Json.decodeFromString<RepositoryDetailArg>(RepositoryDetailArg.serializer(), value)
    }
}

fun RepositoryDetailArg.asNavigationPath(): String {
    return Uri.encode(Json.encodeToString(RepositoryDetailArg.serializer(), this))
}

@Suppress("unused")
val KClass<RepositoryDetailArg>.routePlaceholder: String
    inline get() = "{RepositoryDetailArg}"

fun navRepositoryDetailArg(): NamedNavArgument {
    return navArgument("RepositoryDetailArg") {
        type = RepositoryDetailArg_NavTypeGenerated
    }
}



