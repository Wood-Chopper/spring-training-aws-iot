# Fake device

Simple python script mocking a device behavior. This device get a job, apply a configuration from it and updates it's shadow. Si it's fully integrated with AWS but it not a real device.

## Requirements

* Python 3.6 or higher
* [pipenv](https://pypi.org/project/pipenv/#installation)

## Installation

In this folder `/device`, run:

```shell
pipenv install
```

This will create an environments with the required Python dependencies. To launch this environment, run:
```shell
pipenv shell
```

## Run the device

To run the device, execute this in the pipenv shell:
```shell
python3 apply_config.py --endpoint a27jlub28fxh0a-ats.iot.us-east-1.amazonaws.com --ca_file keys/AmazonRootCA1.pem --cert keys/{quadrigram}-certificate.pem.crt --key keys/{quadrigram}-private.pem.key --thing_name {quadrigram}
```

More examples here: https://github.com/aws/aws-iot-device-sdk-python-v2/tree/main




python3 apply_config.py --endpoint a27jlub28fxh0a-ats.iot.us-east-1.amazonaws.com --ca_file keys/AmazonRootCA1.pem --cert keys/JeromeDevice-certificate.pem.crt --key keys/JeromeDevice-private.pem.key --thing_name JeromeDevice
