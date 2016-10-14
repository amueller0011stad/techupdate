# Security Demos

Erst mal eine einfaches Beispiel f&uuml;r JSON Web Tokens

curl -H 'Content-Type: application/json' -H 'username: demo' -H 'password: pas' -v -X GET http://localhost:8080/JaxRsSecurity/rest/security/authenticate

curl -H 'Content-Type: application/json' -H 'token: siehe Oben' -v -X GET  http://localhost:8080/JaxRsSecurity/rest/security/showallitems

curl -H 'Content-Type: application/json' -H 'token: eyJraWQiOiIxIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJzc2MuZGUiLCJleHAiOjE0NzY0NDg4MzYsImp0aSI6IjJwSlJTdnYtcjlRS3djYzI0TmhLNEEiLCJpYXQiOjE0NzY0NDgyMzYsIm5iZiI6MTQ3NjQ0ODExNiwic3ViIjoiQW5kcmVhcyIsInJvbGVzIjpbImFtZGluIiwidXNlciJdfQ.G1bLzDNBGKa_zRXKUHpEl5ziJOcJfvhvRS_feTFxC5eA3Q0PyrPXlbf7_aixUAzc5-CpH7AfAB76DNyG-KmQqhvYV7viIt0l-xzDcGJRlqhFxRTB1jk9ORJyHcx77mShAqJ7gOld6MR-YyQwJT0iRN5jScPCk28zlOXTRTE5OZOO5lsgTuKVcvCN46MQaK5VdZ6fvu_4B1t7C_YWpyXGtVqKl_7Ey1be9yK39COQndexxVy3H8upRZ5NGtkbgs-7a1pxm5easvllJQvz6JIvnK-nCVSbeohAvL--38B7JpQTGFVKGs2EKATwtcdLjJLghKgeX8yqxOlvTzMBCQ8kGg' -v -X GET http://localhost:8080/JaxRsSecurity/rest/security/showallitems

### Links
- http://cxf.apache.org/docs/jax-rs-oauth2.html
- http://cxf.apache.org/docs/jax-rs-jose.html
- https://avaldes.com/jax-rs-security-using-json-web-tokens-jwt-for-authentication-and-authorization/

 