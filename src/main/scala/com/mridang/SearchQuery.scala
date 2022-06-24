package com.mridang


case class SearchQuery(
                        name: Option[String] = None,
                        segments: Option[Vector[String]] = None,
                        rules: Option[Vector[String]] = None,
                        time: Option[Long] = None,
                        explain: Option[Boolean] = None
                      )
