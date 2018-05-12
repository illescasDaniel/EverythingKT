/* Any copyright is dedicated to the Public Domain.
 * http://creativecommons.org/publicdomain/zero/1.0/ */

package WebExample

import Everything.*
import Everything.WebDocument.StyleSheet

class IndexStyleSheet {
	companion object {
		val value = StyleSheet(
				globalStyles = hashMapOf(
						ElementType.Header to Style(
								Css.backgroundColor to "#e6e6e6",
								Color.white.value,
								Css.fontFamily to "Arial"
						),
						ElementType.Footer to Style(
								Css.backgroundColor to "#e6e6e6",
								Css.color to "#262626",
								*Font(size = 17.px, family = Font.Safe.arial).asProperties
						),
						ElementType.HyperLink to Style(
								Css.opacity to "0.9",
								pseudoClass = PseudoClass.hover
						)
				),
				idStyles = hashMapOf(
						IndexWebID.mainSection to Style(
								Css.marginTop to 4.em.value,
								Css.marginBottom to 4.em.value,
								Css.width to 100.percent.value
						),
						IndexWebID.paypal to Style(
								Css.marginBottom to 5.em.value,
								Css.display to "none",
								Css.margin to 0.75.em.value
						)
				),
				classStyles = hashMapOf(
						IndexWebClass.sectionsWidth to Style(
								Css.width to 50.percent.value
						),
						IndexWebClass.paypalButton to Style(
								Css.height to 2.em.value,
								Css.width to 4.em.value,
								Css.margin to 0.25.em.value
						),
						IndexWebClass.inputPayPal to Style(
								Css.height to 1.75.em.value,
								Css.width to 16.em.value,
								Css.marginLeft to 0.5.em.value
						),
						IndexWebClass.horizontalStack to Style(
								*Flexbox(Flexbox.rowNoWrap, justifyContent = Flexbox.JustifyContent.center, alignItems = Flexbox.AlignItems.center).properties,
								Css.marginLeft to 1.em.value,
								Css.marginRight to 1.em.value,
								Css.height to "3em auto"
						),
						IndexWebClass.horizontalStackSpaced to Style(
								*Flexbox(Flexbox.rowNoWrap, justifyContent = Flexbox.JustifyContent.spaceBetween, alignItems = Flexbox.AlignItems.center).properties,
								Css.marginLeft to 1.em.value,
								Css.marginRight to 1.em.value,
								Css.height to "3em auto"
						),
						IndexWebClass.verticalStack to Style(
								*Flexbox(Flexbox.columnWrap, justifyContent = Flexbox.JustifyContent.center, alignItems = Flexbox.AlignItems.center).properties,
								Css.marginLeft to 1.em.value,
								Css.marginRight to 1.em.value,
								Css.marginTop to 2.em.value,
								Css.height to "3em auto",
								Css.fontFamily to "Arial"
						),
						IndexWebClass.verticalStackWithoutTopMargin to Style(
								*Flexbox(Flexbox.columnWrap, justifyContent = Flexbox.JustifyContent.center, alignItems = Flexbox.AlignItems.center).properties,
								Css.height to "3em auto",
								Css.fontFamily to "Arial"
						),
						IndexWebClass.logoImage to Style(
								Css.height to 8.em.value,
								Css.paddingTop to 3.px.value,
								Css.paddingBottom to 3.px.value,
								Css.alignSelf to "flex-start"
						),
						IndexWebClass.logoBackgroundGradient to Style(
								Css.background to "#262626",
								Css.background to Css.linearGradient(null, SideOrCorner.toRight, colors = arrayListOf("#3399CC", "#7D6FDC", "#C266CC", "#C266CC", "#FF9933")),
								Css.opacity to "0.9"
						),
						IndexWebClass.elementMargin to Style(
								Css.margin to 15.px.value
						),
						IndexWebClass.linkButton to Style(
								Css.backgroundColor to "#404040",
								Css.opacity to "0.8",
								Css.borderRadius to 12.px.value,
								Css.boxShadow to "2px 2px 1px #222222",
								Css.textDecoration to "none",
								Css.color to "inherit"
						)
				)
		)
	}
}