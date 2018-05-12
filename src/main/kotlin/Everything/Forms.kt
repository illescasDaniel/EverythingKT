/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package Everything

import Everything.Common.*

class Label(content: String = "", id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Label, content, id, inlineStyle, cssClass, properties, script, nested)

class Button(var buttonName: String = "",
             var type: Button.Type = Button.Type.button,
             var autofocus: Boolean = true,
             var disabled: Boolean = false,
             var formID: String = "", var formAction: String = "", var formMethod: Form.Method? = null, var formNoValidate: Boolean = false, var formTarget: Target? = null,
             content: String = "", id: CSSID? = null,
             inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
             properties: HashMap<String, String> = hashMapOf(),
             script: Script = Script({}),
             nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Button, content, id, inlineStyle, cssClass, properties, script, nested) {

	enum class Type {
		submit, reset, button, menu;
		val value: String by lazy { this.toString() }
	}

	override val inHTML: String by lazy {

		this.properties = properties.plus(hashMapOf(
				formID.propertyOf("form"),
				formAction.propertyOf("formaction"),
				formMethod?.value.orEmpty().propertyOf("formmethod"),
				formNoValidate.propertyOf("formnovalidate"),
				formTarget?.value.orEmpty().propertyOf("formtarget"),
				type.value.propertyOf("type"),
				buttonName.propertyOf("name"),
				autofocus.propertyOf("autofocus"),
				disabled.propertyOf("disabled"))
		)

		super.inHTML
	}
}

class Input(var label: Label = Label(),
            var placeholder: String = "",
            var legend: String = "",
            var type: Type = Type.text,
            var inputName: String = "",
            var readOnly: Boolean = false,
            id: CSSID? = null,
            inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
            properties: HashMap<String, String> = hashMapOf(),
            script: Script = Script({}),
            nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Input, null, id, inlineStyle, cssClass, properties, script, nested) {

	// TODO: dateTimeLocal
	enum class Type {
		button, checkbox, color, date, dateTimeLocal, email, file,
		hidden, image, month, number, password, radio, range, reset,
		search, submit, tel, text, time, url, week;
		val value: String by lazy { this.toString() }
	}

	override val inHTML: String by lazy {

		this.properties = properties.plus(hashMapOf(
				placeholder.propertyOf("placeholder"),
				"type" to type.value,
				inputName.propertyOf("name"),
				readOnly.propertyOf("readonly")
		))

		label.content = "${label.content} ${super.inHTML}"

		if (this.legend.isNotEmpty()) {
			Element(name = "fieldset", nested = arrayListOf(
					Element("legend", this.legend),
					label
			)).inHTML
		}
		else {
			label.inHTML
		}
	}
}

class Form(var action: String = "", var method: Form.Method = Form.Method.get, var autoComplete: Boolean = true, var target: Target = Target.self,
           var inputs: ArrayList<Input>, var buttons: ArrayList<Button> = arrayListOf(),
           id: CSSID? = null,
           inlineStyle: InlineStyle? = null, cssClass: CSSClass? = null,
           properties: HashMap<String, String> = hashMapOf(),
           script: Script = Script({}),
           nested: ArrayList<Element> = arrayListOf()):
		Element(ElementType.Form, "", id, inlineStyle, cssClass, properties, script, nested) {

	enum class Method {
		get, post;
		val value: String by lazy { this.toString() }
	}

	fun addInputs(vararg inputs: Input) {
		for (input in inputs) {
			this.inputs.add(input)
		}
	}

	fun addButtons(vararg buttons: Button) {
		for (button in buttons) {
			this.buttons.add(button)
		}
	}

	override val inHTML: String by lazy {

		this.nested = ArrayList(this.inputs + this.buttons + nested)

		val autoCompleteFinal = if (autoComplete) "autocomplete" to "on" else "autocomplete" to "off"

		this.properties = properties.plus(hashMapOf(
				action.propertyOf("action"),
				method.value.propertyOf("method"),
				autoCompleteFinal,
				target.value.propertyOf("target")
		))

		super.inHTML
	}
}
