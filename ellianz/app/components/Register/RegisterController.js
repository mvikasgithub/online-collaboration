RegisterModule.controller('RegisterController', ['RegisterService', '$scope', function
(RegisterService, $scope){

    var me = this;
   var self = this;
    self.user={id:null,fname:'',sname:'', address:'',email:'',password:'',city:'',state:'',zip:'',phoneno:'', role:'', active:''};
    self.users=[];
 
    self.id = id;
    self.fname = fname;
    self.sname = sname;
    self.address = address;    
    self.email = email;
    self.password = password;
    self.city = city;
    self.state = state;
    self.zip = zip;
    self.phoneno = phoneno;
    self.role = role;
    self.active = active;


    //this.greeting = 'This message is coming from angular controller';

    RegisterService.fetchAllUsers().then (
        function(users)
        {
            me.Users = users;                
        },

        function(error)
        {
            console.log(error);
        }
    );

}])