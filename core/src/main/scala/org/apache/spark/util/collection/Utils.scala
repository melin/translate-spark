/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.util.collection

import scala.collection.JavaConversions.{collectionAsScalaIterable, asJavaIterator}

import com.google.common.collect.{Ordering => GuavaOrdering}

/**
 * Utility functions for collections.
 */
/**
 * 集合的公共函数.
 */
private[spark] object Utils {

  /**
   * Returns the first K elements from the input as defined by the specified implicit Ordering[T]
   * and maintains the ordering.
   */
  /**
   * 从这个特定的的满足隐式转换特质Ordering[T]类型定义的输入返回这个开始的K个元素并且保持顺序.
   */
  def takeOrdered[T](input: Iterator[T], num: Int)(implicit ord: Ordering[T]): Iterator[T] = {
    val ordering = new GuavaOrdering[T] {
      override def compare(l: T, r: T) = ord.compare(l, r)
    }
    collectionAsScalaIterable(ordering.leastOf(asJavaIterator(input), num)).iterator
  }
}
