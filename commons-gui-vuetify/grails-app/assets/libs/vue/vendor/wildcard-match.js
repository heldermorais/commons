!function(e,r){"object"==typeof exports&&"undefined"!=typeof module?module.exports=r():"function"==typeof define&&define.amd?define(r):(e="undefined"!=typeof globalThis?globalThis:e||self).wcmatch=r()}(this,(function(){"use strict";function e(e){return"-"===e||"^"===e||"$"===e||"+"===e||"."===e||"("===e||")"===e||"|"===e||"["===e||"]"===e||"{"===e||"}"===e||"*"===e||"?"===e||"\\"===e?"\\"+e:e}function r(t,n){if(void 0===n&&(n=!0),Array.isArray(t))return"(?:"+t.map((function(e){return"^"+r(e,n)+"$"})).join("|")+")";var o="",i="",a=".";!0===n?(o="/",i="[/\\\\]",a="[^/\\\\]"):n&&(a=(i=function(r){for(var t="",n=0;n<r.length;n++)t+=e(r[n]);return t}(o=n)).length>1?"((?!"+(i="(?:"+i+")")+").)":"[^"+i+"]");for(var s=n?i+"+?":"",f=n?i+"*?":"",u=n?t.split(o):[t],p="",g=0;g<u.length;g++){var l=u[g],y=u[g+1],c="";if(l||!(g>0))if(n&&(c=g===u.length-1?f:"**"!==y?s:""),n&&"**"===l)c&&(p+=0===g?"":c,p+="(?:"+a+"*?"+c+")*?");else{for(var h=0;h<l.length;h++){var d=l[h];"\\"===d?h<l.length-1&&(p+=e(l[h+1]),h++):p+="?"===d?a:"*"===d?a+"*?":e(d)}p+=c}}return p}function t(e,r){if("string"!=typeof r)throw new TypeError("Sample must be a string, but "+typeof r+" given");return e.test(r)}return function(e,n){if("string"!=typeof e&&!Array.isArray(e))throw new TypeError("The first argument must be a single pattern string or an array of patterns, but "+typeof e+" given");if("string"!=typeof n&&"boolean"!=typeof n||(n={separator:n}),2===arguments.length&&void 0!==n&&("object"!=typeof n||null===n||Array.isArray(n)))throw new TypeError("The second argument must be an options object or a string/boolean separator, but "+typeof n+" given");if("\\"===(n=n||{}).separator)throw new Error("\\ is not a valid separator because it is used for escaping. Try setting the separator to `true` instead");var o=r(e,n.separator),i=new RegExp("^"+o+"$",n.flags),a=t.bind(null,i);return a.options=n,a.pattern=e,a.regexp=i,a}}));
//# sourceMappingURL=index.umd.js.map