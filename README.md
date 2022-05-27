export AWS_PROFILE=mridang
copilot init 

```
Welcome to the Copilot CLI! We're going to walk you through some questions
to help you get set up with a containerized application on AWS. An application is a collection of
containerized services that operate together.

Use existing application: No
Application name: searchy
Workload type: Load Balanced Web Service
Service name: app
Dockerfile: ./Dockerfile
Port: 9999
Ok great, we'll set up a Load Balanced Web Service named app in application searchy listening on port 9999.

✔ Created the infrastructure to manage services and jobs under application searchy.

✔ The directory copilot will hold service manifests for application searchy.

✔ Wrote the manifest for service app at copilot/app/manifest.yml
Your manifest contains configurations like your container size and port (:9999).

⠴ Creating ECR repositories for service app.
```
