[...document.getElementsByClassName('edit-form')].forEach(el=>{
	el.style = 'display:none';
});

if(document.getElementById('hallId'))document.getElementById('hallId').value=0;

const getMax = (hallId, tickets)=>{
	console.log(tickets);
	tickets = tickets.filter(t=>t.hall.id==hallId);
	console.log(tickets);
	console.log('rows number : '+tickets[0].hall.rowsNumber);
	console.log('seats number : '+tickets[0].hall.seatsNumber);
	console.log('seat id : '+tickets[0].seats.id);
	document.getElementById('rowNumber').max = tickets[0].hall.rowsNumber;
	document.getElementById('seatNumber').max = tickets[0].hall.seatsNumber;
	document.getElementById('seatId').value = parseInt(tickets[0].seats.id);
}
const showEditForm = (id)=>{
	if(document.getElementById(`edit-form-${id}`)){
		document.getElementById(`edit-form-${id}`).style = 'display:block';
		document.getElementById(`edit-form-link-${id}`).style = 'display:none';
	}
}
const hideEditForm = (id)=>{
	if(document.getElementById(`edit-form-${id}`)){
		document.getElementById(`edit-form-${id}`).style = 'display:none';
		document.getElementById(`edit-form-link-${id}`).style = 'display:block';

	}
}
const showSessionEditForm = (id)=>{
	if(document.getElementById(`session-edit-form-${id}`)){
		document.getElementById(`session-edit-form-${id}`).style = 'display:block';
		document.getElementById(`edit-form-link-${id}`).style = 'display:none';
	}
}
const hideSessionEditForm = (id)=>{
	if(document.getElementById(`session-edit-form-${id}`)){
		document.getElementById(`session-edit-form-${id}`).style = 'display:none';
		document.getElementById(`edit-form-link-${id}`).style = 'display:block';
	}
}
const showTicketsEditForm = (id)=>{
	if(document.getElementById(`tickets-edit-form-${id}`)){
		document.getElementById(`tickets-edit-form-${id}`).style = 'display:block';
		document.getElementById(`edit-form-link-${id}`).style = 'display:none';
	}
}
const hideTicketsEditForm = (id)=>{
	if(document.getElementById(`tickets-edit-form-${id}`)){
		document.getElementById(`tickets-edit-form-${id}`).style = 'display:none';
		document.getElementById(`edit-form-link-${id}`).style = 'display:block';
	}
}