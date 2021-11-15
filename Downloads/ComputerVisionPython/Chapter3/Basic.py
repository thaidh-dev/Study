import cv2
import mediapipe as mp
import time

cap = cv2.VideoCapture("FaceDetectionVideo/head-pose-face-detection-female-and-male.mp4")
pTime = 0

mpFaceDetection = mp.solutions.face_detection
mpDraw = mp.solutions.drawing_utils
# MIN_DETECTION_CONFIDENCE: Minimum confidence value ([0.0, 1.0]) from the face detection model for the detection to be considered successful. Default to 0.5.
faceDetection = mpFaceDetection.FaceDetection(min_detection_confidence=0.75)

while True:
    success, img = cap.read()

    imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    results = faceDetection.process(imgRGB)

    if results.detections:
        for id, detection in enumerate(results.detections):
            # cách 1: face dectection với các điểm landmark, vẽ = mediapipe
            # mpDraw.draw_detection(img, detection)

            # cách 2: vẽ ko có điểm landmark, chỉ có face box bao quanh mặt, vẽ = cv2
            box = detection.location_data.relative_bounding_box
            imgH, imgW, imgC = img.shape
            # tọa độ, chiều dài rộng của face box tính theo pixel
            boxPixel = int(box.xmin * imgW), int(box.ymin * imgH), \
                       int(box.width * imgW), int(box.height * imgH)
            cv2.rectangle(img, boxPixel, (255, 0, 255), 2)
            # detection.score[0] * 100: % xác định đây là face
            cv2.putText(img, str(int(detection.score[0] * 100)) + "%", (boxPixel[0], boxPixel[1] - 10),
                        cv2.FONT_HERSHEY_PLAIN, 2, (255, 0, 255), 2)

    cTime = time.time()
    if cTime - pTime != 0:
        fps = 1 / (cTime - pTime)
        pTime = cTime
        cv2.putText(img, str(int(fps)), (20, 50), cv2.FONT_HERSHEY_PLAIN, 2, (255, 0, 0), 2)
    else:
        print("0")

    cv2.imshow("Image", img)
    cv2.waitKey(7)
