package com.alphaz.core.file

import com.alphaz.infrastructure.domain.BaseDO
import javax.persistence.Entity
import javax.persistence.Table

/**
 *@Author: c0der
 *@Date: 下午2:38 2018/3/16
 *@Description:
 */
@Entity
@Table(name = "alphaz_picture", catalog = "")
data class Picture(var brief: String?,
                   var extension: String?,
                   var file: ByteArray?,
                   var name: String?,
                   var url: String?) : BaseDO<Picture, Long>() {
    constructor() : this(null, null, null, null, null)
}