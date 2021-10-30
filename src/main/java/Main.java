import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;
import software.amazon.awssdk.services.ec2.model.RebootInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RebootInstancesResponse;

/**
 * To run this Java V2 code example, ensure that you have setup your development environment, including your credentials.
 * <p>
 * For information, see this documentation topic:
 * <p>
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */
public class Main {

    public static void main(String[] args) {

        final String instanceId = "i-0b12e7a3305094887";
        final Region region = Region.AP_SOUTHEAST_1;

        Ec2Client ec2 = Ec2Client.builder()
                .region(region)
                .build();

        rebootEC2Instance(ec2, instanceId);
        ec2.close();
    }

    public static void rebootEC2Instance(Ec2Client ec2, String instanceId) {

        try {
            System.out.println("Start rebooting instance! " + instanceId);
            RebootInstancesRequest request = RebootInstancesRequest.builder()
                    .instanceIds(instanceId)
                    .build();

            RebootInstancesResponse response = ec2.rebootInstances(request);
            System.out.printf(
                    "Successfully rebooted instance %s - response: %s", instanceId, response.toString());
        } catch (Ec2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
