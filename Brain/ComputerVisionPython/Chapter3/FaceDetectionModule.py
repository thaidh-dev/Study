import cv2
import mediapipe as mp
import time


class FaceDetector():
    def __init__(self, min_detection_confidence=0.5):
        self.min_detection_confidence = min_detection_confidence
        self.mpFaceDetection = mp.solutions.face_detection
        self.mpDraw = mp.solutions.drawing_utils
        # MIN_DETECTION_CONFIDENCE: Minimum confidence value ([0.0, 1.0]) from the face detection model for the detection to be considered successful. Default to 0.5.
        self.faceDetection = self.mpFaceDetection.FaceDetection(self.min_detection_confidence)

    def findFaces(self, img, draw=True):
        imgRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
        self.results = self.faceDetection.process(imgRGB)

        if self.results.detections:
            for id, detection in enumerate(self.results.detections):
                # cách 1: face dectection với các điểm landmark, vẽ = mediapipe
                # mpDraw.draw_detection(img, detection)

                # cách 2: vẽ ko có điểm landmark, chỉ có face box bao quanh mặt, vẽ = cv2
                box = detection.location_data.relative_bounding_box
                imgH, imgW, imgC = img.shape
                # tọa độ, chiều dài rộng của face box tính theo pixel
                boxPixel = int(box.xmin * imgW), int(box.ymin * imgH), \
                           int(box.width * imgW), int(box.height * imgH)

                if draw:
                    self.fancyDraw(img, boxPixel)
                    # detection.score[0] * 100: % xác định đây là face
                    cv2.putText(img, str(int(detection.score[0] * 100)) + "%", (boxPixel[0], boxPixel[1] - 10),
                                cv2.FONT_HERSHEY_PLAIN, 2, (255, 0, 255), 2)

    def fancyDraw(self, img, boxPixel, length=30, thickness=3, rthickness=1):
        x, y, w, h = boxPixel
        x1, y1 = x + w, y + h

        cv2.rectangle(img, boxPixel, (255, 0, 255), rthickness)
        # top left
        cv2.line(img, (x, y), (x + length, y), (255, 0, 255), thickness)
        cv2.line(img, (x, y), (x, y + length), (255, 0, 255), thickness)
        # top right
        cv2.line(img, (x1, y), (x1 - length, y), (255, 0, 255), thickness)
        cv2.line(img, (x1, y), (x1, y + length), (255, 0, 255), thickness)
        # bottom left
        cv2.line(img, (x, y1), (x + length, y1), (255, 0, 255), thickness)
        cv2.line(img, (x, y1), (x, y1 - length), (255, 0, 255), thickness)
        # bottom right
        cv2.line(img, (x1, y1), (x1 - length, y1), (255, 0, 255), thickness)
        cv2.line(img, (x1, y1), (x1, y1 - length), (255, 0, 255), thickness)


def main():
    cap = cv2.VideoCapture("FaceDetectionVideo/head-pose-face-detection-female-and-male.mp4")
    pTime = 0
    detector = FaceDetector()

    while True:
        success, img = cap.read()
        detector.findFaces(img)

        cTime = time.time()
        if cTime - pTime != 0:
            fps = 1 / (cTime - pTime)
            pTime = cTime
            cv2.putText(img, str(int(fps)), (20, 50), cv2.FONT_HERSHEY_PLAIN, 2, (255, 0, 0), 2)
        else:
            print("0")

        cv2.imshow("Image", img)
        cv2.waitKey(7)


if __name__ == '__main__':
    main()
