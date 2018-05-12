if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Kotlin2jsOutput_END'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Kotlin2jsOutput_END'.");
}
var Kotlin2jsOutput_END = function (_, Kotlin) {
  'use strict';
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  var throwCCE = Kotlin.throwCCE;
  var Enum = Kotlin.kotlin.Enum;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var throwISE = Kotlin.throwISE;
  var equals = Kotlin.equals;
  var Unit = Kotlin.kotlin.Unit;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  IndexWebClass.prototype = Object.create(Enum.prototype);
  IndexWebClass.prototype.constructor = IndexWebClass;
  IndexWebID.prototype = Object.create(Enum.prototype);
  IndexWebID.prototype.constructor = IndexWebID;
  function CSSClass() {
  }
  CSSClass.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CSSClass',
    interfaces: []
  };
  function CSSID() {
  }
  CSSID.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'CSSID',
    interfaces: []
  };
  function elementWith($receiver, id) {
    var tmp$;
    return (tmp$ = document.getElementById(id.toString())) == null || Kotlin.isType(tmp$, HTMLElement) ? tmp$ : throwCCE();
  }
  function IndexWebClass(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function IndexWebClass_initFields() {
    IndexWebClass_initFields = function () {
    };
    IndexWebClass$sectionsWidth_instance = new IndexWebClass('sectionsWidth', 0);
    IndexWebClass$paypalButton_instance = new IndexWebClass('paypalButton', 1);
    IndexWebClass$inputPayPal_instance = new IndexWebClass('inputPayPal', 2);
    IndexWebClass$horizontalStack_instance = new IndexWebClass('horizontalStack', 3);
    IndexWebClass$horizontalStackSpaced_instance = new IndexWebClass('horizontalStackSpaced', 4);
    IndexWebClass$verticalStack_instance = new IndexWebClass('verticalStack', 5);
    IndexWebClass$verticalStackWithoutTopMargin_instance = new IndexWebClass('verticalStackWithoutTopMargin', 6);
    IndexWebClass$logoImage_instance = new IndexWebClass('logoImage', 7);
    IndexWebClass$logoBackgroundGradient_instance = new IndexWebClass('logoBackgroundGradient', 8);
    IndexWebClass$elementMargin_instance = new IndexWebClass('elementMargin', 9);
    IndexWebClass$linkButton_instance = new IndexWebClass('linkButton', 10);
  }
  var IndexWebClass$sectionsWidth_instance;
  function IndexWebClass$sectionsWidth_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$sectionsWidth_instance;
  }
  var IndexWebClass$paypalButton_instance;
  function IndexWebClass$paypalButton_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$paypalButton_instance;
  }
  var IndexWebClass$inputPayPal_instance;
  function IndexWebClass$inputPayPal_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$inputPayPal_instance;
  }
  var IndexWebClass$horizontalStack_instance;
  function IndexWebClass$horizontalStack_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$horizontalStack_instance;
  }
  var IndexWebClass$horizontalStackSpaced_instance;
  function IndexWebClass$horizontalStackSpaced_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$horizontalStackSpaced_instance;
  }
  var IndexWebClass$verticalStack_instance;
  function IndexWebClass$verticalStack_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$verticalStack_instance;
  }
  var IndexWebClass$verticalStackWithoutTopMargin_instance;
  function IndexWebClass$verticalStackWithoutTopMargin_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$verticalStackWithoutTopMargin_instance;
  }
  var IndexWebClass$logoImage_instance;
  function IndexWebClass$logoImage_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$logoImage_instance;
  }
  var IndexWebClass$logoBackgroundGradient_instance;
  function IndexWebClass$logoBackgroundGradient_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$logoBackgroundGradient_instance;
  }
  var IndexWebClass$elementMargin_instance;
  function IndexWebClass$elementMargin_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$elementMargin_instance;
  }
  var IndexWebClass$linkButton_instance;
  function IndexWebClass$linkButton_getInstance() {
    IndexWebClass_initFields();
    return IndexWebClass$linkButton_instance;
  }
  IndexWebClass.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IndexWebClass',
    interfaces: [CSSClass, Enum]
  };
  function IndexWebClass$values() {
    return [IndexWebClass$sectionsWidth_getInstance(), IndexWebClass$paypalButton_getInstance(), IndexWebClass$inputPayPal_getInstance(), IndexWebClass$horizontalStack_getInstance(), IndexWebClass$horizontalStackSpaced_getInstance(), IndexWebClass$verticalStack_getInstance(), IndexWebClass$verticalStackWithoutTopMargin_getInstance(), IndexWebClass$logoImage_getInstance(), IndexWebClass$logoBackgroundGradient_getInstance(), IndexWebClass$elementMargin_getInstance(), IndexWebClass$linkButton_getInstance()];
  }
  IndexWebClass.values = IndexWebClass$values;
  function IndexWebClass$valueOf(name) {
    switch (name) {
      case 'sectionsWidth':
        return IndexWebClass$sectionsWidth_getInstance();
      case 'paypalButton':
        return IndexWebClass$paypalButton_getInstance();
      case 'inputPayPal':
        return IndexWebClass$inputPayPal_getInstance();
      case 'horizontalStack':
        return IndexWebClass$horizontalStack_getInstance();
      case 'horizontalStackSpaced':
        return IndexWebClass$horizontalStackSpaced_getInstance();
      case 'verticalStack':
        return IndexWebClass$verticalStack_getInstance();
      case 'verticalStackWithoutTopMargin':
        return IndexWebClass$verticalStackWithoutTopMargin_getInstance();
      case 'logoImage':
        return IndexWebClass$logoImage_getInstance();
      case 'logoBackgroundGradient':
        return IndexWebClass$logoBackgroundGradient_getInstance();
      case 'elementMargin':
        return IndexWebClass$elementMargin_getInstance();
      case 'linkButton':
        return IndexWebClass$linkButton_getInstance();
      default:throwISE('No enum constant IndexWebClass.' + name);
    }
  }
  IndexWebClass.valueOf_61zpoe$ = IndexWebClass$valueOf;
  function IndexWebID(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function IndexWebID_initFields() {
    IndexWebID_initFields = function () {
    };
    IndexWebID$mainSection_instance = new IndexWebID('mainSection', 0);
    IndexWebID$paypal_instance = new IndexWebID('paypal', 1);
    IndexWebID$showPayPalOptions_instance = new IndexWebID('showPayPalOptions', 2);
    IndexWebID$changelogTable_instance = new IndexWebID('changelogTable', 3);
    IndexWebID$twoEurPayPal_instance = new IndexWebID('twoEurPayPal', 4);
    IndexWebID$fiveEurPaypal_instance = new IndexWebID('fiveEurPaypal', 5);
    IndexWebID$customEurPayPal_instance = new IndexWebID('customEurPayPal', 6);
    IndexWebID$testParagraph_instance = new IndexWebID('testParagraph', 7);
    IndexWebID$testParagraph2_instance = new IndexWebID('testParagraph2', 8);
  }
  var IndexWebID$mainSection_instance;
  function IndexWebID$mainSection_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$mainSection_instance;
  }
  var IndexWebID$paypal_instance;
  function IndexWebID$paypal_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$paypal_instance;
  }
  var IndexWebID$showPayPalOptions_instance;
  function IndexWebID$showPayPalOptions_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$showPayPalOptions_instance;
  }
  var IndexWebID$changelogTable_instance;
  function IndexWebID$changelogTable_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$changelogTable_instance;
  }
  var IndexWebID$twoEurPayPal_instance;
  function IndexWebID$twoEurPayPal_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$twoEurPayPal_instance;
  }
  var IndexWebID$fiveEurPaypal_instance;
  function IndexWebID$fiveEurPaypal_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$fiveEurPaypal_instance;
  }
  var IndexWebID$customEurPayPal_instance;
  function IndexWebID$customEurPayPal_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$customEurPayPal_instance;
  }
  var IndexWebID$testParagraph_instance;
  function IndexWebID$testParagraph_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$testParagraph_instance;
  }
  var IndexWebID$testParagraph2_instance;
  function IndexWebID$testParagraph2_getInstance() {
    IndexWebID_initFields();
    return IndexWebID$testParagraph2_instance;
  }
  IndexWebID.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'IndexWebID',
    interfaces: [CSSID, Enum]
  };
  function IndexWebID$values() {
    return [IndexWebID$mainSection_getInstance(), IndexWebID$paypal_getInstance(), IndexWebID$showPayPalOptions_getInstance(), IndexWebID$changelogTable_getInstance(), IndexWebID$twoEurPayPal_getInstance(), IndexWebID$fiveEurPaypal_getInstance(), IndexWebID$customEurPayPal_getInstance(), IndexWebID$testParagraph_getInstance(), IndexWebID$testParagraph2_getInstance()];
  }
  IndexWebID.values = IndexWebID$values;
  function IndexWebID$valueOf(name) {
    switch (name) {
      case 'mainSection':
        return IndexWebID$mainSection_getInstance();
      case 'paypal':
        return IndexWebID$paypal_getInstance();
      case 'showPayPalOptions':
        return IndexWebID$showPayPalOptions_getInstance();
      case 'changelogTable':
        return IndexWebID$changelogTable_getInstance();
      case 'twoEurPayPal':
        return IndexWebID$twoEurPayPal_getInstance();
      case 'fiveEurPaypal':
        return IndexWebID$fiveEurPaypal_getInstance();
      case 'customEurPayPal':
        return IndexWebID$customEurPayPal_getInstance();
      case 'testParagraph':
        return IndexWebID$testParagraph_getInstance();
      case 'testParagraph2':
        return IndexWebID$testParagraph2_getInstance();
      default:throwISE('No enum constant IndexWebID.' + name);
    }
  }
  IndexWebID.valueOf_61zpoe$ = IndexWebID$valueOf;
  function EndScripts() {
    EndScripts$Companion_getInstance();
  }
  function EndScripts$Companion() {
    EndScripts$Companion_instance = this;
    this.paypalScript_0 = EndScripts$Companion$paypalScript$lambda;
    this.all = [this.paypalScript_0];
  }
  function EndScripts$Companion$paypalScript$lambda$lambda(closure$paypalModalSection, closure$showPayPalOptionsButton) {
    return function (it) {
      var tmp$, tmp$_0, tmp$_1;
      if (equals((tmp$ = closure$paypalModalSection != null ? closure$paypalModalSection.style : null) != null ? tmp$.display : null, '') || equals((tmp$_0 = closure$paypalModalSection != null ? closure$paypalModalSection.style : null) != null ? tmp$_0.display : null, 'none')) {
        closure$paypalModalSection.style.display = 'flex';
        closure$showPayPalOptionsButton != null ? (closure$showPayPalOptionsButton.textContent = 'Hide Options') : null;
      }
       else {
        (tmp$_1 = closure$paypalModalSection != null ? closure$paypalModalSection.style : null) != null ? (tmp$_1.display = 'none') : null;
        closure$showPayPalOptionsButton != null ? (closure$showPayPalOptionsButton.textContent = 'Show Options') : null;
      }
      return Unit;
    };
  }
  function EndScripts$Companion$paypalScript$lambda$lambda_0(it) {
    return window.open('https://www.paypal.me/illescasDaniel/2');
  }
  function EndScripts$Companion$paypalScript$lambda$lambda_1(it) {
    return window.open('https://www.paypal.me/illescasDaniel/5');
  }
  function EndScripts$Companion$paypalScript$lambda$lambda_2(closure$customEurPayPal) {
    return function (it) {
      var tmp$;
      var keyboardEvent = Kotlin.isType(tmp$ = it, KeyboardEvent) ? tmp$ : throwCCE();
      if (equals(keyboardEvent.key, 'Enter')) {
        var moneyValue = closure$customEurPayPal.value;
        if (equals(moneyValue, '')) {
          alert('Invalid Input');
        }
         else {
          window.open('https://www.paypal.me/illescasDaniel/' + moneyValue);
        }
      }
      return Unit;
    };
  }
  function EndScripts$Companion$paypalScript$lambda() {
    var tmp$;
    var paypalModalSection = elementWith(document, IndexWebID$paypal_getInstance());
    var showPayPalOptionsButton = elementWith(document, IndexWebID$showPayPalOptions_getInstance());
    showPayPalOptionsButton != null ? (showPayPalOptionsButton.onclick = EndScripts$Companion$paypalScript$lambda$lambda(paypalModalSection, showPayPalOptionsButton)) : null;
    var twoEurPayPal = elementWith(document, IndexWebID$twoEurPayPal_getInstance());
    twoEurPayPal != null ? (twoEurPayPal.onclick = EndScripts$Companion$paypalScript$lambda$lambda_0) : null;
    var fiveEurPayPal = elementWith(document, IndexWebID$fiveEurPaypal_getInstance());
    fiveEurPayPal != null ? (fiveEurPayPal.onclick = EndScripts$Companion$paypalScript$lambda$lambda_1) : null;
    var customEurPayPal = (tmp$ = elementWith(document, IndexWebID$customEurPayPal_getInstance())) == null || Kotlin.isType(tmp$, HTMLInputElement) ? tmp$ : throwCCE();
    return customEurPayPal != null ? (customEurPayPal.addEventListener('keydown', EndScripts$Companion$paypalScript$lambda$lambda_2(customEurPayPal)), Unit) : null;
  }
  EndScripts$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: []
  };
  var EndScripts$Companion_instance = null;
  function EndScripts$Companion_getInstance() {
    if (EndScripts$Companion_instance === null) {
      new EndScripts$Companion();
    }
    return EndScripts$Companion_instance;
  }
  EndScripts.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'EndScripts',
    interfaces: []
  };
  function main(args) {
    var tmp$, tmp$_0;
    tmp$ = EndScripts$Companion_getInstance().all;
    for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
      var script = tmp$[tmp$_0];
      script();
    }
  }
  var package$Everything = _.Everything || (_.Everything = {});
  var package$Common = package$Everything.Common || (package$Everything.Common = {});
  package$Common.CSSClass = CSSClass;
  package$Common.CSSID = CSSID;
  package$Common.elementWith_x2hg3l$ = elementWith;
  Object.defineProperty(IndexWebClass, 'sectionsWidth', {
    get: IndexWebClass$sectionsWidth_getInstance
  });
  Object.defineProperty(IndexWebClass, 'paypalButton', {
    get: IndexWebClass$paypalButton_getInstance
  });
  Object.defineProperty(IndexWebClass, 'inputPayPal', {
    get: IndexWebClass$inputPayPal_getInstance
  });
  Object.defineProperty(IndexWebClass, 'horizontalStack', {
    get: IndexWebClass$horizontalStack_getInstance
  });
  Object.defineProperty(IndexWebClass, 'horizontalStackSpaced', {
    get: IndexWebClass$horizontalStackSpaced_getInstance
  });
  Object.defineProperty(IndexWebClass, 'verticalStack', {
    get: IndexWebClass$verticalStack_getInstance
  });
  Object.defineProperty(IndexWebClass, 'verticalStackWithoutTopMargin', {
    get: IndexWebClass$verticalStackWithoutTopMargin_getInstance
  });
  Object.defineProperty(IndexWebClass, 'logoImage', {
    get: IndexWebClass$logoImage_getInstance
  });
  Object.defineProperty(IndexWebClass, 'logoBackgroundGradient', {
    get: IndexWebClass$logoBackgroundGradient_getInstance
  });
  Object.defineProperty(IndexWebClass, 'elementMargin', {
    get: IndexWebClass$elementMargin_getInstance
  });
  Object.defineProperty(IndexWebClass, 'linkButton', {
    get: IndexWebClass$linkButton_getInstance
  });
  _.IndexWebClass = IndexWebClass;
  Object.defineProperty(IndexWebID, 'mainSection', {
    get: IndexWebID$mainSection_getInstance
  });
  Object.defineProperty(IndexWebID, 'paypal', {
    get: IndexWebID$paypal_getInstance
  });
  Object.defineProperty(IndexWebID, 'showPayPalOptions', {
    get: IndexWebID$showPayPalOptions_getInstance
  });
  Object.defineProperty(IndexWebID, 'changelogTable', {
    get: IndexWebID$changelogTable_getInstance
  });
  Object.defineProperty(IndexWebID, 'twoEurPayPal', {
    get: IndexWebID$twoEurPayPal_getInstance
  });
  Object.defineProperty(IndexWebID, 'fiveEurPaypal', {
    get: IndexWebID$fiveEurPaypal_getInstance
  });
  Object.defineProperty(IndexWebID, 'customEurPayPal', {
    get: IndexWebID$customEurPayPal_getInstance
  });
  Object.defineProperty(IndexWebID, 'testParagraph', {
    get: IndexWebID$testParagraph_getInstance
  });
  Object.defineProperty(IndexWebID, 'testParagraph2', {
    get: IndexWebID$testParagraph2_getInstance
  });
  _.IndexWebID = IndexWebID;
  Object.defineProperty(EndScripts, 'Companion', {
    get: EndScripts$Companion_getInstance
  });
  _.EndScripts = EndScripts;
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('Kotlin2jsOutput_END', _);
  return _;
}(typeof Kotlin2jsOutput_END === 'undefined' ? {} : Kotlin2jsOutput_END, kotlin);
