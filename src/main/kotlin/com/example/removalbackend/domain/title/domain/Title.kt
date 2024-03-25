package com.example.removalbackend.domain.title.domain

import javax.persistence.*

@Entity(name = "tbl_title")
class Title(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title_id")
    val titleId: Long,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    var title: String,
) {
    constructor() : this(
        titleId = 1,
        title = "kjfalkhjgleak"
    )
}