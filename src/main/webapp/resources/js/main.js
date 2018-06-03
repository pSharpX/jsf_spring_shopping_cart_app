/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
	'use strict';
	var localeCode = $('#localeCode').val();
	var selectedLocaleCode = $('.selectedLocaleCode').val();
		
	$('[data-toggle="offcanvas"]').on('click', function() {
		$('.offcanvas-collapse').toggleClass('open');
	});

	$('.datepicker').datepicker({
		language: selectedLocaleCode,
		todayHighlight: true,
		format : 'dd/mm/yyyy',
		endDate : '0d'
	});

    $("input[type='file']").fileinput({
        language: selectedLocaleCode,
        theme: "fa",
        showUpload: false,
        allowedFileExtensions: ["jpg", "png", "gif"]
    });
	
});