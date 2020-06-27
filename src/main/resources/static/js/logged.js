$(document).ready(function() {
	$.ajax({
		url: "/getUserByEmail?email=" + emailInput,
		async: false
	}).done(function(data) {
		if(data !== null && data !== ""){
			//duplicate
			notDuplicate = false;
		}
	});
	
	
	//Setup Year-Month-Day
	var todayYear = new Date().getFullYear();
	$('select[name=year]').append('<option value="" disabled selected>Year</option>');
	for(var i = todayYear; i >= 1900; i--) {
	    $('select[name=year]').append("<option value='"+i+"'>"+i+"</option>");
	}
	$('select[name=month]').append('<option value="" disabled selected>Month</option>');
	for(var i = 1; i <= 12; i++) {
	    $('select[name=month]').append("<option value='"+i+"'>"+i+"</option>");
	}
	$('select[name=date]').append('<option value="" disabled selected>Date</option>');
	$('select[name=year]').on('change', function(){
		if($('select[name=month]').val() === null){
			$('select[name=month]').val(1);
		}
	    checkTotalDay();
	});
	$('select[name=month]').on('change', function(){
	    checkTotalDay();
	});
	
	
	//Form Validation	
	$("#formRegistration").validate({
		rules: {
			mobile: {required: true, mobileUnique: true, mobileValid: true},
			firstName: {required: true},
			lastName: {required: true},
			email: {email: true, required: true, emailUnique: true},
		},
		messages: {
			mobile: {
				required: "Phone Number is required",
				mobileUnique: "Phone Number is exist, please input other phone number",
				mobileValid: "Please enter valid Indonesian phone number (+628xxx or 08xxx)"
			},
			firstName:{
				required: "First Name is required"
			},
			lastName:{
				required: "Last Name is required"
			},
			email:{
				required: "Email is required",
				emailUnique: "Email is exist, please input other email",
			}
		},
		tooltip_options: {
			mobile: { placement:'top' }
		},
	});
	customRuleValidator();
	
	
	//Form Submit
	$("#formSubmit").click(function(e){
		if(!$("#formRegistration").valid()){
			return;
		}
		
		var firstName = $("#firstName").val();
		var lastName = $("#lastName").val();
		var mobile= $("#mobile").val();
		var year = $("#dropdownYear").val();
		var month = $("#dropdownMonth").val();
		var day = $("#dropdownDate").val();
		var email = $("#email").val();
		var gender = $("input[name=gender]:checked").val();
		
		var date = null;
		if(day !== null){
			date = new Date(year, month, day);
		}
		
		var data ={
				firstName : firstName,
				lastName: lastName,
				mobile : mobile,
				date: date,
				email: email,
				gender: gender,
		};
		$.ajax({
			  type: "POST",
			  url: "/save",
			  data: JSON.stringify(data),
			  contentType: "application/json"
		}).done(function(e){
			$(".panel-body").addClass("disabledbutton");
			$(".panel-footer").show();
		});
	});
	
	//Login Redirect
	$("#btnLogin").click(function(e){
		window.location.href = "/login";
	});
});

//------------------- FUNCTION -------------------------

function checkTotalDay() {
	var year = $('select[name=year]').val();
    var month = $('select[name=month]').val();
    var totalDate = 31;
    if(year !== '' && month !== '') {
        totalDate = new Date(year, month, 0).getDate();
    }
    $('select[name=date]').empty();
    $('select[name=date]').append('<option value="" disabled selected>Date</option>');
    for(var i = 1; i <= totalDate; i++) {
       $('select[name=date]').append("<option value='"+i+"'>"+i+"</option>");
    }
}

function customRuleValidator(){
	jQuery.validator.addMethod("mobileUnique", function(value, element) {
		var notDuplicate = true;
		$.ajax({
			url: "/getUserByMobile?mobile=" + value,
			async: false
		}).done(function(data) {
			if(data !== null && data !== ""){
				//duplicate
				notDuplicate = false;
			}
		});
		return notDuplicate;
	});
	
	jQuery.validator.addMethod("mobileValid", function(value, element) {
		return /^(^\+62\s?|^08)(\d{3,4}-?){2}\d{3,4}$/.test(value)
	});
	
	jQuery.validator.addMethod("emailUnique", function(value, element) {
		var notDuplicate = true;
		$.ajax({
			url: "/getUserByEmail?email=" + value,
			async: false
		}).done(function(data) {
			if(data !== null && data !== ""){
				//duplicate
				notDuplicate = false;
			}
		});
		return notDuplicate;
	});
	
}