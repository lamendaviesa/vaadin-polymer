var data = {
	headers : [
		{
			group: false,
			title: 'Material',
			style: ''
		},
		{
			group: true,
			title: 'Place',
			subheaders: [ {title: 'Arrival'}, {title: 'Departure'}],
			style: '"color:red;"'
		}
	],
	
	rows: [
		{ 
			Material: 'madera',
			Place: {
				Arrival: 'Mendavia',
				Departure: 'Sesma'
			}
		},
		{ 
			Material: 'hierro',
			Place: {
				Arrival: 'Viana',
				Departure: 'Mendavia'
			}
		}
		
	]
		
		
}