
$(window).on('load', function() {
	$(document).click(function(e) {
		var $target = $(e.target);
		if ($target.is($target.closest('.w3-modal'))) {
			$target.hide();
		}
	});
});

$(document).ready(function() {
	$('.datepicker').datePicker({
	weekDays: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa']
	, months: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
		, readValue: function(element) {
			if (!element.value) {
				var d = new Date();
				var s = moment(d).format('YYYY-MM-DD'); 
				return s; // initial time if empty
			}
			var result = moment(element.value, 'DD/MM/YYYY').format('YYYY-MM-DD');
			return result; // triggers default behavior
		}
		, renderValue: function(container, element, value) {
			element.value = moment(value, 'YYYY-MM-DD').format('DD/MM/YYYY');
		}
	});
	$('.timepicker').datePicker({
		timeFormat: "HH:MM AM"
		, readValue: function(element) {
			if (!element.value) {
				var d = new Date();
				var s = dateFormat(d, "h'h'MM"); 
				return s; // initial time if empty
			}
			return element.value; // triggers default behavior
		}
	});
});

$(document).keypress(function(e) {
	if (e.keyCode == 27) {
		$('.w3-modal').filter(':visible').hide();
	}
});

/*
 * http://blog.stevenlevithan.com/archives/date-time-format Date Format 1.2.3
 * (c) 2007-2009 Steven Levithan <stevenlevithan.com> MIT license
 * 
 * Includes enhancements by Scott Trenda <scott.trenda.net> and Kris Kowal
 * <cixar.com/~kris.kowal/>
 * 
 * Accepts a date, a mask, or a date and a mask. Returns a formatted version of
 * the given date. The date defaults to the current date/time. The mask defaults
 * to dateFormat.masks.default.
 */

var dateFormat = function () {
	var	token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
		timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
		timezoneClip = /[^-+\dA-Z]/g,
		pad = function (val, len) {
			val = String(val);
			len = len || 2;
			while (val.length < len) val = "0" + val;
			return val;
		};

	// Regexes and supporting functions are cached through closure
	return function (date, mask, utc) {
		var dF = dateFormat;

		// You can't provide utc if you skip other args (use the "UTC:" mask prefix)
		if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
			mask = date;
			date = undefined;
		}

		// Passing date through Date applies Date.parse, if necessary
		date = date ? new Date(date) : new Date;
		if (isNaN(date)) throw SyntaxError("invalid date");

		mask = String(dF.masks[mask] || mask || dF.masks["default"]);

		// Allow setting the utc argument via the mask
		if (mask.slice(0, 4) == "UTC:") {
			mask = mask.slice(4);
			utc = true;
		}

		var	_ = utc ? "getUTC" : "get",
			d = date[_ + "Date"](),
			D = date[_ + "Day"](),
			m = date[_ + "Month"](),
			y = date[_ + "FullYear"](),
			H = date[_ + "Hours"](),
			M = date[_ + "Minutes"](),
			s = date[_ + "Seconds"](),
			L = date[_ + "Milliseconds"](),
			o = utc ? 0 : date.getTimezoneOffset(),
			flags = {
				d:    d,
				dd:   pad(d),
				ddd:  dF.i18n.dayNames[D],
				dddd: dF.i18n.dayNames[D + 7],
				m:    m + 1,
				mm:   pad(m + 1),
				mmm:  dF.i18n.monthNames[m],
				mmmm: dF.i18n.monthNames[m + 12],
				yy:   String(y).slice(2),
				yyyy: y,
				h:    H % 12 || 12,
				hh:   pad(H % 12 || 12),
				H:    H,
				HH:   pad(H),
				M:    M,
				MM:   pad(M),
				s:    s,
				ss:   pad(s),
				l:    pad(L, 3),
				L:    pad(L > 99 ? Math.round(L / 10) : L),
				t:    H < 12 ? "a"  : "p",
				tt:   H < 12 ? "am" : "pm",
				T:    H < 12 ? "A"  : "P",
				TT:   H < 12 ? "AM" : "PM",
				Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
				o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
				S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
			};

		return mask.replace(token, function ($0) {
			return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
		});
	};
}();

// Some common format strings
dateFormat.masks = {
	"default":      "ddd mmm dd yyyy HH:MM:ss",
	shortDate:      "m/d/yy",
	mediumDate:     "mmm d, yyyy",
	longDate:       "mmmm d, yyyy",
	fullDate:       "dddd, mmmm d, yyyy",
	shortTime:      "h:MM TT",
	mediumTime:     "h:MM:ss TT",
	longTime:       "h:MM:ss TT Z",
	isoDate:        "yyyy-mm-dd",
	isoTime:        "HH:MM:ss",
	isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
	isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
dateFormat.i18n = {
	dayNames: [
		"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat",
		"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
	],
	monthNames: [
		"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec",
		"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
	]
};

function parseTime(timeString) {
	if (timeString == '') return null;
	var d = new Date();
	var time = timeString.match(/(\d+)(:(\d\d))?\s*([Pp]?)/);
	d.setHours( parseInt(time[1]) + ( ( parseInt(time[1]) < 12 && time[4] ) ? 12 : 0) );
	d.setMinutes( parseInt(time[3]) || 0 );
	d.setSeconds(0, 0);
	return d;
}

function enleverLueur($input) {
			$input.removeClass('lueurSuccès');
	$input.removeClass('lueurErreur');
}

function ajouterRemplacer($input) {
	var idListe = $input.attr('data-id'); 
		$liste = null; 
	if(idListe != null) {
	}
	$form = $input.closest('form');
	$icone = $input.prev('i');
	$icone.addClass('w3-spin-fast');
	$icone.show();
	$.ajax({
		url: $form.attr('action')
		, type: 'GET'
		, timeout: 10000
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, data: $form.serialize()
		, success: function(json, statusText, xhr, $form) { 
			for (var key in json) {
				if (data.hasOwnProperty(key)) {
					var str = data[key];
					$elem = $('#' + idListe); 
					if($elem != null) {
						$elem.replaceWith(str); 
					}
				}
			}
			$icone.removeClass('w3-spin-fast');
		} 
		, error: function()  { 
			$icone.removeClass('w3-spin-fast');
		} 
	}); 
	return false; 
}

function suggere($input) {
	var idListe = $input.attr('data-id'); 
		$liste = null; 
	if(idListe != null) {
		$liste = $('#' + idListe); 
	}
	$form = $input.closest('form');
	$icone = $input.prev('i');
	$icone.addClass('w3-spin-fast');
	$icone.show();
	$.ajax({
		url: $form.attr('action')
		, type: 'GET'
		, timeout: 5000
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, data: $form.serialize()
		, success: function(json, statusText, xhr, $form) { 
			for (var id in json) {
				if (json.hasOwnProperty(id)) {
					var str = json[id];
					$elem = $('#' + id); 
					if($elem != null) {
						$elem.replaceWith(str); 
					}
				}
			}
//			if($liste != null) {
//				$liste.replaceWith(responseText); 
//			}
			$icone.removeClass('w3-spin-fast');
		} 
		, error: function()  { 
			$icone.removeClass('w3-spin-fast');
		} 
	}); 
	return false; 
}

function envoyerFormulaire($inputEnfant, $lueur) {
	$form = $inputEnfant.closest('form');
	$icone = $form.prev('i');
	$icone.addClass('w3-spin-fast');
	$icone.removeClass('w3-hide');
	$.ajax({
		url: $form.attr('action')
		, type: 'POST'
		, contentType: 'application/x-www-form-urlencoded; charset=UTF-8'
		, data: $form.serialize()
		, success: function(json, statusText, xhr, $form) { 
			for (var id in json) {
				if (json.hasOwnProperty(id)) {
					var str = json[id];
					$elem = $('#' + id); 
					if($elem != null) {
						$elem.replaceWith(str); 
					}
				}
			}

//			var idParent = $inputEnfant.attr('data-idParent'); 
//			if(idParent != null) {
//				$inputParent = $('#' + idParent); 
//				if(idParent != null) {
//					$inputEnfant.prop('checked', $inputEnfant.prop('checked')); 
//					envoyerFormulaire($inputParent, $lueur); 
//				}
//			}
//			else if($lueur != null)
				$lueur.addClass('lueurSuccès');
				$icone.removeClass('w3-spin-fast');
				$icone.addClass('w3-hide');
		} 
		, error: function()  { 
			$lueur.addClass('lueurErreur');
			document.getElementById('modaleErreur').style.display='block'; 
			$icone.removeClass('w3-spin-fast');
			$icone.addClass('w3-hide');
		} 
	}); 
	return false; 
}

//Used to toggle the menu on small screens when clicking on the menu button
function toggleFunction(s) {
	var x = document.getElementById(s);
	if (x.className.indexOf("w3-show") == -1) {
		x.className += " w3-show";
	} else {
		x.className = x.className.replace(" w3-show", "");
	}
}

