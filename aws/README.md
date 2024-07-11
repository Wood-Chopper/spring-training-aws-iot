# AWS Access Set Up

## Sign in to AWS

[Sign-in URL](https://jeromenavez.signin.aws.amazon.com/console)

User name: {Your first name}

Your password will be provided separately by your teacher.

## Create your device

### Steps

1. Open https://us-east-1.console.aws.amazon.com/iot/home?region=us-east-1#/thinghub
1. Choose **Create Things**
1. Choose **Create single thing**
2. Enter a Thing name
3. Leave the rest as default
4. Keep **Auto-generate a new certificate (recommended)** selected
5. In Policies, select **iot-only**
6. Click "Create Thing"
7. Download the certificates and keys in `device/keys`

### Verify device setup

Go to [Device's Readme](../device/README.md) and follow the readme.

The last log should be:
```text
Request to start next job was accepted, but there are no jobs to be done. Waiting for further jobs...
```

## Create IAM access keys

### Requirements
* [AWS CLI installed](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

### Steps

1. Go to [IAM User list](https://us-east-1.console.aws.amazon.com/iam/home?region=us-east-1#/users)
2. Select you user
3. Click **Create access key**
4. Choose **Command Line Interface (CLI)**
5. Confirm the checkbox and click **Next**
6. Click **Create access key**
7. You should see your keys **Keep the browser page as it is**
8. Open a terminal

Run:
```shell
aws configure --profile spring-training

Enter your Access Key ID: <your_access_key_id>
Enter your Secret Access Key: <your_secret_access_key>
Enter your default region name: us-east-1
Enter your default output format: json
```
Copy your access key and secret key from the browser.

### Verify AWS CLI set up

This command should not fail:

```shell
aws iot create-job --job-id test-set-up --targets arn:aws:iot:us-east-1:459293086294:thing/{ThingName} --document "{ \"id\": 1}" --region us-east-1 --profile spring-training
```

Replace `{ThingName}` by your device name as previously set up.

