/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import kotlin.js.Json

interface QueryInterface {
	val inSQL: String
}

interface FromInterface: QueryInterface, LimitInterface {
	fun from(vararg table: String): WhereInterface
}

interface WhereInterface: QueryInterface, OrderByInterface {
	fun where(condition: String): AndOrWhereInteface
}

interface AndOrWhereInteface: GroupByInterface, OrderByInterface, LimitInterface {
	fun andWhere(condition: String): AndOrWhereInteface
	fun orWhere(condition: String): AndOrWhereInteface
	fun andNotWhere(condition: String): AndOrWhereInteface
	fun orNotWhere(condition: String): AndOrWhereInteface
}

interface GroupByInterface: QueryInterface, LimitInterface {
	fun groupBy(vararg str: String): GroupByOrderInterface
}

interface GroupByOrderInterface: QueryInterface, HavingInterface, LimitInterface, OrderByInterface {
	val ascending: HavingInterface
	val descending: HavingInterface
}

interface HavingInterface: OrderByInterface {
	fun having(condition: String): AndOrHavingInterface
}

interface AndOrHavingInterface: QueryInterface, LimitInterface {
	fun andHaving(condition: String): AndOrHavingInterface
	fun orHaving(condition: String): AndOrHavingInterface
	fun andNotHaving(condition: String): AndOrHavingInterface
	fun orNotHaving(condition: String): AndOrHavingInterface
}

interface OrderByInterface: QueryInterface, LimitInterface {
	fun orderBy(vararg columns: String): OrderByOrderInterface
	fun orderBy(vararg columns: SQLColumn): OrderByOrderInterface
}

interface OrderByOrderInterface: LimitInterface {
	val ascendingOrder: QueryInterface
	val descendingOrder: QueryInterface
}

// FIXME: not f working
interface LimitInterface: QueryInterface {
	fun limit(rowCount: Int, offset: Int = -1): QueryInterface
}

class SQL {

	class Query {

		companion object: FromInterface, WhereInterface, AndOrWhereInteface, GroupByInterface, OrderByOrderInterface, GroupByOrderInterface, AndOrHavingInterface, LimitInterface, HavingInterface, OrderByInterface {

			fun average(arg: String) = " AVG($arg) "
			fun bitAnd(arg: String) = " BIT_AND($arg) "
			fun bitOr(arg: String) = " BIT_OR($arg) "
			fun bitXor(arg: String) = " BIT_XOR($arg) "
			fun count(arg: String) = " COUNT($arg) "
			fun countDistinct(arg: String) = " COUNT(DISTINCT $arg) "
			fun concatenated(arg: String) = " GROUP_CONCAT($arg) "
			fun maximum(arg: String) = " MAX($arg) "
			fun minimum(arg: String) = " MIN($arg) "
			fun standardDeviation(arg: String) = " STD($arg) "
			fun sum(arg: String) = " SUM($arg) "
			fun populationVariance(arg: String) = " VAR_POP($arg) "
			fun sampleVariance(arg: String) = " VAR_SAMP($arg) "
			fun variance(arg: String) = " VARIANCE($arg) "

			private var query: String = ""

			fun select(vararg columns: String): FromInterface {
				query = "SELECT ${columns.joinToString(", ")}"
				return this
			}

			fun select(vararg columns: SQLColumn): FromInterface {
				query = "SELECT ${columns.joinToString(", ") { it.name }}"
				return this
			}

			override fun from(vararg table: String): WhereInterface {
				query += " FROM ${table.joinToString(", ")}"
				return this
			}

			override fun where(condition: String): AndOrWhereInteface {
				query += " WHERE `$condition`"
				return this
			}

			override fun andWhere(condition: String): AndOrWhereInteface {
				query += " AND `$condition`"
				return this
			}

			override fun orWhere(condition: String): AndOrWhereInteface {
				query += " OR `$condition`"
				return this
			}

			override fun andNotWhere(condition: String): AndOrWhereInteface {
				query += " AND NOT `$condition`"
				return this
			}

			override fun orNotWhere(condition: String): AndOrWhereInteface {
				query += " OR NOT `$condition`"
				return this
			}

			override fun groupBy(vararg str: String): GroupByOrderInterface {
				query += " GROUP BY ${str.joinToString(", ")}"
				return this
			}

			override val ascending: HavingInterface by lazy {
				query += " ASC "
				this
			}

			override val descending: HavingInterface by lazy {
				query += " DESC "
				this
			}

			override fun having(condition: String): AndOrHavingInterface {
				query += " HAVING `$condition`"
				return this
			}

			override fun andHaving(condition: String): AndOrHavingInterface {
				query += " AND `$condition`"
				return this
			}

			override fun orHaving(condition: String): AndOrHavingInterface {
				query += " OR `$condition`"
				return this
			}

			override fun andNotHaving(condition: String): AndOrHavingInterface {
				query += " AND NOT `$condition`"
				return this
			}

			override fun orNotHaving(condition: String): AndOrHavingInterface {
				query += " OR NOT `$condition`"
				return this
			}

			override fun orderBy(vararg columns: String): OrderByOrderInterface {
				query += " ORDER BY ${columns.joinToString(", ")}"
				return this
			}

			override fun orderBy(vararg columns: SQLColumn): OrderByOrderInterface {
				query += " ORDER BY  ${columns.joinToString(", ") { it.name }}"
				return this
			}

			override val ascendingOrder: QueryInterface by lazy {
				query += " ASC "
				this
			}
			override val descendingOrder: QueryInterface by lazy {
				query += " DESC "
				this
			}

			override fun limit(rowCount: Int, offset: Int): QueryInterface {
				query += " LIMIT $rowCount ${if (offset > -1) ",$offset" else ""}"
				return this
			}

			//
			override val inSQL: String by lazy {
				this.query
			}

			override fun toString(): String {
				return this.inSQL
			}
		}
	}

	// class Insert

	// class Delete
}

typealias Query = SQL.Query

//

open class SQLTable(row: Json) {

	var properties: dynamic = object {}

	init {

		try {
			this.properties = row.asDynamic()

			if (this.properties == null || this.properties === undefined) {
				throw Exception("Couldn not get the columns from the row")
			}

		} catch (e: Exception) {
			println(e.cause)
			println(e.message)
		}
	}
}

/*data class Column(val field: String, val type: String, val isNull: String, val key: String?, val defaultValue: String?, val extra: String?) {

	constructor(resultRow: Json): this(resultRow["Field"] as String, resultRow["Type"] as String, resultRow["Null"] as String, resultRow["Key"] as String?, resultRow["Default"] as String?, resultRow["Extra"] as String?)
}

class Changelog(private val row: Json) {
	/*private var promisedRows = IndexDB.connection.runPromised("SHOW columns FROM Changelog")
	private var columns = ArrayList<Column>()*/

	private val columnNames = arrayListOf("id", "version", "description", "date")
	var properties: dynamic = object {}

	init {
		/*promisedRows.then { results ->
			for (resultRow in results.rows) {
				this.columns.add(Column(resultRow))
			}
		}*/

		try {
			for (columnName in columnNames) {
				this.properties[columnName] = row[columnName]
			}
		} catch (e: Exception) {
			println("Couldn't find column name")
			println(e.cause)
			println(e.message)
		}
	}
}
*/

//

open class SQLName(var name: String) {
	init {
		this.name = this.name.trim(' ', '\n', '\r')

		if (this.name.isBlank() || this.name.contains(" ") || this.name.contains("\n")) {
			throw Exception("Invalid column name")
		}
	}
}

class SQLColumn(name: String): SQLName(name)