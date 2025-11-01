class A {
    int v1;
    String name;
    A(){
        this("No name",0);
    }
    A(String n){
        this(0);
        name = n;
    }
    A(String n, int num){
        name = n;
        v1 = num;
    }
    A(int num){
        // this("No name"); ---> Recursive constructor invocation is not allowed
        this("No name",num);
    }
}


