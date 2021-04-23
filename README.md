## RewardPoints

click on here: http://localhost:3333/swagger-ui.html#/

**It will load Screen as below, with following POST and GET methods**
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/HomeScreen.PNG)

**Now post data on /calculateRewards**
##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Rewards_Post.PNG)

**we will be getting reward points Response as below for customer ID1,ID2,ID3**
##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Rewards_Post_Response.PNG)

**/getRewards Mock=True will return the reward points based on the mock data we have given in data.json**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Mock_True.PNG)

**Mock=True Response as below**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Mock_True_Response.PNG)

**Mock=False will return the reward points from Database**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Mock_False.PNG)

**Mock=False Response as below, we will be getting this reward points based on the transactions we have posted in database**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Mock_False_Response.PNG)

**Below is the data I have posted in database, It will calculate reward points for amounts 10,500,500**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/DataBase_Amount.PNG)

**Post data on /transaction with the below data by giving customerID as CUST4**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Transaction_Post.PNG)

**CUST4 data will be storing in database as below, we can get reward points for this data from /getRewards**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Transaction_SavedToDatabase.PNG)

**/findAll is used to fetch all the data that is stored in database, we have four customer records stored in database**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Transaction_FindAll_Response.PNG)

**I have written junits by mocking data**

##
![screenshot](https://github.com/nikhileshch/RewardPoints/blob/main/images/Junits.PNG)


