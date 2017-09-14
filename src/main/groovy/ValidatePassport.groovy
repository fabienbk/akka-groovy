import actor.msg.CallSmartEyeResult
import actor.msg.CallVelocityCheckResult
import api.ServiceAPI
import api.Service
import pojo.Document
import pojo.Identity

/**
 * This script runs a document validation workflow against an identity
 */
def main(ServiceAPI api, Identity identity, Document document) {

    println("Running script...")

    resultAsync = null

    api.callAsync(Service.VELOCITY_CHECK, { CallVelocityCheckResult result ->
        println("velocity result : " + result.getVelocityStatus())
        resultAsync = result
    })

    CallSmartEyeResult resultSync = api.call(Service.SMARTEYE)
    println("smart eye result ok")

    println("Waiting for completion...")
    api.waitForCompletion()

    println("results= $resultAsync and $resultSync")

}