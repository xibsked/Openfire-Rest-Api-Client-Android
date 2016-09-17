# Openfire-Rest-Api-Client-Android
Android REST API Client for the Openfire to manage Openfire instances by sending an REST/HTTP request to the server

##Client is deveploed for the App- [Openfire Admin](https://play.google.com/store/apps/details?id=com.sked.ofadmin)
[![Google Play](http://developer.android.com/images/brand/en_generic_rgb_wo_60.png)](https://play.google.com/store/apps/details?id=com.sked.ofadmin)

![screenshots](http://lh3.googleusercontent.com/Jhq76egYE6jDs8InQRY2xz_Y2PEJ04lhiy1DKdH1ktgcJFVt-ZdtI-37t3F3Y3N0yOw=h310-rw)![screenshots](http://lh3.googleusercontent.com/PO59cEezJVqPQdCEVq4_qDDF1Le7uLJjJ6Ht0UCAUfZhEOUXTlK6K1cAglnzvqAuXas=h310-rw)![screenshots](http://lh3.googleusercontent.com/5QXbXZoNUzCgPCrdebTwdoQ8uf1PV6UgFB3Z1xe35C1GnXU-RYryT-xzn-yzJ49fHQ=h310-rw)![screenshots](http://lh3.googleusercontent.com/w6cSh1dPMtg_-sQ2o3M7cJQdkGvFkC6qZXNV-YmX4t7e_Y1xmRcA2DbmFPloS6lRe2k=h310-rw)

### How to add OfRestClient to project
Pretty simple - 

  1. Add 'ofrestclient' module to your project.
  2. Enable Rest plugin from Openfire web console

### How to make request 
Its jsut most simplified version of volley-

        <!--/*Create a account with the host and ports*/-->
        Account account = new Account("192.168.1.3", 9090);
        <!--/*Create an authenticationToken with either using admin credentials or sharedSecretKey*/-->
        AuthenticationToken authenticationToken = new AuthenticationToken("admin", "admin");
        account.setAuthenticationToken(authenticationToken);
        <!--/*Request OfApiClient with the account and callbacks*/-->
        OfApiClient.with(this).account(account).getUser("admin",
                new Listener<User>() {
                    @Override
                    public void onResponse(Object mTag, User user) {
                        //Handle Success
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Handle Failure
                    }
                });
  
  
