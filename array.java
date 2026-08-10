public class array
{
    public static void main(String[]args)
    {
        
        students[]= {"Bilal","Adnan","Gani","Faiz","obaid"};
        for(int i =0;i<5;i++)
        {
            System
        }
        int nums[] = new int[4]; // 1d array
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 4;
        
        for(int i=0;i<4;i++)
        {
            System.out.println(nums[i]);
        }
        
        int nums2[][] = new int[3][4];//2d array
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                nums2[i][j]=(int)(Math.random()*10);
                // assigning random value to 2d array via random method in Math class 
            }
        }
        // displaying 2d array  
        for(int i = 0; i<3;i++)
        {
            for(int j=0;j<4;j++)
            {
                System.out.print(nums2[i][j]); //displays one line at a time 
            }
            System.out.println(); // moves loop to next line   
        }
        
        // enhanced way to display arrays via loops
        for(int n[]: nums2)
        {
            for(int m : n)
            {
                System.out.print(m+" ");
            }
            System.out.println();
        }
    }
}