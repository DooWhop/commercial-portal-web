 $(function(){
	 var clickflag = true;
$(".jsoup_modify").dblclick(function() {
	//	alert(this);
	
	if(!clickflag){
		return;
		}
	
//	$(this).submit(function(e){
//		  alert("Submitted");
//		});  
	
		clickflag=false;
		var element = this;
        var oldhtml = element.innerText;
        //创建新的input元素
        var newobj = document.createElement('textarea');
        //为新增元素添加类型
        newobj.type = 'text';
        //为新增元素添加value值
        newobj.style.hight="auto";
        newobj.style.width="500px"; 
        newobj.value = oldhtml;
       
        //设置该标签的子节点为空
        element.innerHTML = '';
        //添加该标签的子节点，input对象
        element.appendChild(newobj);
        //设置选择文本的内容或设置光标位置（两个参数：start,end；start为开始位置，end为结束位置；如果开始位置和结束位置相同则就是光标位置）
        newobj.setSelectionRange(0, oldhtml.length);
        //设置获得光标
        newobj.focus();
        
        //为新增元素添加光标离开事件
        newobj.onblur = function() {
        	clickflag=true;
            //当触发时判断新增元素值是否为空，为空则不修改，并返回原有值 
            element.innerHTML = this.value == oldhtml ? oldhtml : this.value;
            //当触发时设置父节点的双击事件为ShowElement
           // element.setAttribute("ondblclick", "ShowElement(this);");
            $.ajax({
    		    type: "POST",		    
    		    url: "modify?method=modify"+"&date="+new Date().getTime(),
    		    data:{
    		    	id:newobj.id,
    		    	oldhtml: oldhtml,
    		    	newhtml:newobj.value
    		    },
    		       success: function(data){
    		    	   alert(data); 	
    		    	 //  window.location.reload(true); 
    		               },
    		               error: function(){
    	             //       $("#show").html("Error XMLHttpRequest");
    	                }  		                
    	            });
        }
       // alert(this);
        //设置父节点的双击事件为空
      //  newobj.parentNode.setAttribute("ondblclick", "");
     //   request.setAttribute("tagObj",this);
    });
});