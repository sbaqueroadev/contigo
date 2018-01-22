function Cartesian (canvas,ctx) {
    this.zeroPosition = {x: 0,y: 0};
    this.context = ctx;
    this.canvas = canvas;
    this.color = 'hsla(0, 0%, 0%, 1)';
    this.lineWidth = 2;
    this.selected = false;
    this.aux = {};
    this.onDrawListener = undefined;
}
 
Cartesian.prototype.divisionHeight = 6;
Cartesian.prototype.divisionsSpace = 40;

Cartesian.prototype.locate = function() {
	this.aux.lineWidth = this.context.width;
	this.aux.strokeStyle = this.context.color;
	//this.context.strokeStyle = 'hsla(0, 0%, 0%, 1)';
    //this.context.lineWidth = 2;
    this.selected = true;
	var me = this;
    this.canvas.addEventListener("mouseup", function(event){
    	if(me.selected){
    		me.selected = false;
    		me.zeroPosition = {x: event.clientX, y: event.clientY};
	    	me.draw();
    	}
    });
};



Cartesian.prototype.draw = function() {
	this.context.beginPath();
	this.context.moveTo(0, this.zeroPosition.y);
	this.context.lineTo(canvas.width, this.zeroPosition.y);
    this.context.moveTo(this.zeroPosition.x, 0);
    this.context.lineTo(this.zeroPosition.x, canvas.height);
    for(var x=this.zeroPosition.x+this.divisionsSpace;x<canvas.width;x+=this.divisionsSpace){
    	this.context.moveTo(x, this.zeroPosition.y-this.divisionHeight);
    	this.context.lineTo(x, this.zeroPosition.y+this.divisionHeight);
    }
    for(var x=this.zeroPosition.x-this.divisionsSpace;x>0;x-=this.divisionsSpace){
    	this.context.moveTo(x, this.zeroPosition.y-this.divisionHeight);
    	this.context.lineTo(x, this.zeroPosition.y+this.divisionHeight);
    }
    for(var y=this.zeroPosition.y+this.divisionsSpace;y<canvas.height;y+=this.divisionsSpace){
    	this.context.moveTo(this.zeroPosition.x-this.divisionHeight,y);
    	this.context.lineTo(this.zeroPosition.x+this.divisionHeight,y);
    }
    for(var y=this.zeroPosition.y-this.divisionsSpace;y>0;y-=this.divisionsSpace){
    	this.context.moveTo(this.zeroPosition.x-this.divisionHeight,y);
    	this.context.lineTo(this.zeroPosition.x+this.divisionHeight,y);
    }
    this.context.stroke();
    this.context.strokeStyle = this.aux.strokeStyle;
    this.context.lineWidth = this.aux.lineWidth;
    if(this.onDrawListener){
    	this.onDrawListener(this);
    }
    
};

