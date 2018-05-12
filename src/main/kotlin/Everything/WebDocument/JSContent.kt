/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything.WebDocument

import Everything.Script

open class JSContent(var head: ArrayList<Script> = arrayListOf(),
                     var body: ArrayList<Script> = arrayListOf(),
                     var end: ArrayList<Script> = arrayListOf())

typealias ScriptCode = () -> Unit
open class JSContent2(var head: ArrayList<ScriptCode> = arrayListOf(),
                     var body: ArrayList<ScriptCode> = arrayListOf(),
                     var end: ArrayList<ScriptCode> = arrayListOf())