package com.gabriel.kspanel.project.rest.utils.user

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

typealias PermissionsBuilder = () -> List<Int>

object Permission {

    const val Administrator = 0x7FFFFDFF
    const val CreateAssignments = 0x00000002
    const val ManageAccounts = 0x00000003

}

@Serializable(with = PermissionsSerializer::class)
class PermissionSet(var mask: Int) {

    operator fun plus(permission: Int): PermissionSet = PermissionSet(this.mask or permission)

    operator fun plusAssign(permission: Int) { mask = mask or permission }

    operator fun minus(permission: Int): PermissionSet = PermissionSet(mask xor (mask and permission))

    operator fun minusAssign(permission: Int) { mask = mask xor (mask and permission) }

    operator fun contains(permission: Int): Boolean =
        this.mask and permission == permission

    companion object {
        inline operator fun invoke(base: PermissionSet = PermissionSet(0), block: PermissionsBuilder): PermissionSet = block().map {
            base += it
        }.let { base }
    }
}

object PermissionsSerializer: KSerializer<PermissionSet> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("permission", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder): PermissionSet =
        PermissionSet(decoder.decodeInt())

    override fun serialize(encoder: Encoder, value: PermissionSet) =
        encoder.encodeInt(value.mask)
}