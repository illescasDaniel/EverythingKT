/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*

class Row(vararg var columnRowValue: Pair<String, Any> = arrayOf(),
          id: CSSID? = null,
          inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
          properties: HashMap<String, String> = hashMapOf(),
          script: Script = Script({}),
          nestedElements: ArrayList<Element> = arrayListOf()):
		Element(ElementType.TableRow, "", id, inlineStyle, cssClass, properties, script, nestedElements) {

	override val inHTML: String by lazy {

		val columns = this.columnRowValue.mapTo(ArrayList()) { Element("td", content = it.second.toString()) }
		this.nested = ArrayList(columns + this.nested)

		super.inHTML
	}
}

class Table(var columns: MutableSet<String> = mutableSetOf(),
            var rows: ArrayList<Row> = arrayListOf(),
            var headStyle: InlineStyle? = null,
            var bodyStyle: InlineStyle? = null,
            id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nestedElements: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Table, "", id, inlineStyle, cssClass, properties, script, nestedElements) {

	fun add(row: Row) {
		val content = LinkedHashMap<String, Any>()
		row.columnRowValue.forEach { content[it.first] = it.second }

		if (content.keys == columns) {
			this.rows.add(row)
		}
	}

	fun addRow(vararg columnValues: Any, id: CSSID? = null, inlineStyle: InlineStyle? = null) {

		if (columnValues.count() == this.columns.count()) {

			val rowHashMap: LinkedHashMap<String, Any> = linkedMapOf()
			val columnsArray = this.columns.toTypedArray()

			for (i in 0 until columnValues.count()) {
				rowHashMap[columnsArray[i]] = columnValues[i]
			}

			this.rows.add(Row(*rowHashMap.toList().toTypedArray(), id = id, inlineStyle = inlineStyle))
		}
	}

	fun addRows(vararg rows: Array<Any>) {
		rows.forEach { row -> addRow(*row) }
	}

	override val inHTML: String by lazy {

		val tableColumns = this.columns.mapTo(ArrayList()) { Element("th", content = it) }
		val head = Element("thead", inlineStyle = headStyle, nested = tableColumns)
		val body = Element("tbody", inlineStyle = bodyStyle, nested = rows as ArrayList<Element>)

		this.nested = ArrayList(arrayListOf(head, body) + this.nested)
		super.inHTML
	}
}
